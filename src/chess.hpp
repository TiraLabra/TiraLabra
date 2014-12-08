
#pragma once

#include <cstring>
using namespace std;

enum pieceType {
    PT_Empty,
    PT_Pawn,
    PT_Bishop,
    PT_Knight,
    PT_Rook,
    PT_Queen,
    PT_King
};

enum pieceColor { PC_Empty, PC_White, PC_Black };

#define PC_OTHER(x) ((x) == PC_White ? PC_Black : PC_White)

class chessPiece;
class chessBoard;
class moveTree;
#include "structures.hpp"


class chessPiece {
public:
    chessPiece() {}

    chessPiece(pieceType _type, pieceColor _color)
        : type(_type), color(_color), name(pieceName(_type)),
          chtype(pieceLetter(_type, _color)) {}

    /**
     * Returns the chess notation character for a given piece
     * @param ptype piece ID
     * @return name of pawn
     */
    inline char pieceLetter(pieceType t, pieceColor color) {
        char ch = ' ';
        switch (t) {
        case PT_Pawn: ch = 'P'; break;
        case PT_Bishop: ch = 'B'; break;
        case PT_Knight: ch = 'N'; break;
        case PT_Rook: ch = 'R'; break;
        case PT_Queen: ch = 'Q'; break;
        case PT_King: ch = 'K'; break;
        default: ch = ' '; break;
        }
        if (color == PC_Black) 
            return tolower(ch);
        return ch;
    }

    /**
     * Returns the name of the given piece type
     * @param ptype piece ID
     * @return name of pawn
     */
    inline const char* pieceName(pieceType ptype) {
        switch (ptype) {
        case PT_Pawn: return "Pawn";
        case PT_Bishop: return "Bishop";
        case PT_Knight: return "Knight";
        case PT_Rook: return "Rook";
        case PT_Queen: return "Queen";
        case PT_King: return "King";
        default: return "empty";
        }
    }

    pieceType type;
    pieceColor color;
    const char* name;
    char chtype;
};

#define defPiece(ptype)                         \
    chessPiece ptype##W(PT_##ptype, PC_White);  \
    chessPiece ptype##B(PT_##ptype, PC_Black);

chessPiece empty(PT_Empty, PC_Empty);
defPiece(Pawn);
defPiece(Bishop);
defPiece(Knight);
defPiece(Rook);
defPiece(Queen);
defPiece(King);

#undef defPiece

typedef chessPiece gameState_t[8][8];

/**
 * Stores the state of the game board
 */
class chessBoard {
public:
    chessBoard() {
        memcpy(gameState, startingState, sizeof(gameState_t));
        currentPlayer = PC_White;
    }

    chessBoard(const chessBoard &other) {
        memcpy(gameState, other.gameState, sizeof(gameState_t));
        currentPlayer = other.currentPlayer;

    }

    void printFEN() {
        for (int y = 7; y >= 0; y--) {
            int e = 0;
            for (int x = 0; x < 8; x++) {
                chessPiece &p = gameState[x][y];
                if (p.type == PT_Empty) e++;
                else if (e != 0) {printf("%d",e); }

                if (p.type != PT_Empty) {
                    printf("%c", p.chtype);
                    e=0;
                    
                }
                
            }
            if (e != 0) {printf("%d",e); e=0;}
            if (y == 0) printf(" ");
            else printf("/");
        }

        if (currentPlayer == PC_White)
            printf("w");
        else
            printf("b");
        printf(" - -\n");
        
    }

    chessPiece findType(char c) {
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                if (startingState[x][y].chtype == c)
                    return startingState[x][y];
        return empty;
    }

    void readFEN(char *fen) {
        if (strstr(fen, " w ")) currentPlayer = PC_White;
        else if (strstr(fen, " b ")) currentPlayer = PC_Black;

        char *p = strtok(fen, "/");
        int x = 0, y = 7;
        while (p != NULL && y >= 0) {
            while (*p != 0) {
                if (x >= 8) break;
                if (isdigit(*p)) {
                    for (int n = *p - '0'; n > 0; n--)
                        gameState[x++][y] = empty;
                }
                else
                    gameState[x++][y] = findType(*p);
                p++;
            }
            p = strtok(NULL, "/");
            x=0;
            y--;
        }
    }
    

    /**
     * Prints out the textual representation of the chessboard.
     */
    void print() {
        cout << "\n    a   b   c   d   e   f   g   h  ";
        cout << "\n  ---------------------------------\n";
        for (int j = 7; j >= 0; j--) {
            cout << j+1 << " |";
            for (int i = 0; i < 8; i++) {
                chessPiece &piece = gameState[i][j];
                cout << " ";
                if (piece.type == PT_Empty)
                    cout << "  |";
                else
                    cout << piece.chtype << " |";
            }
            cout << "\n  ---------------------------------\n";
        }
        cout << "    a   b   c   d   e   f   g   h  \n";
    }

    bool readMove() {
        string movestr;
        int x1, y1, x2, y2;
        
        for (;;) {
            cin >> movestr;
            if (movestr.length() != 4) {
                cout << "Invalid move \"" + movestr + "\"" << endl;
                continue;
            }
            
            x1 = movestr[0] - 'a';
            y1 = movestr[1] - '0' - 1;
            x2 = movestr[2] - 'a';
            y2 = movestr[3] - '0' - 1;
            // printf("trying move [ %d, %d ] -> [ %d, %d ]\n", x1, y1, x2, y2);

            if (doMove(x1,y1,x2,y2)) {
                break;
            }
            else
                cout << "Invalid move \"" + movestr + "\"" << endl;
            
        } 
        
    }

    /**
     * Evaluates the worth of a given game state for the current player or the opponent.
     * @param color which player to evaluate position for
     * @return value of position
     */
    int evaluate(pieceColor color) {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessPiece &piece = gameState[i][j];
                if (piece.type == PT_Empty) continue;
                
                int pieceValue = piece.type * 20;

                if (piece.color == color)
                    score += pieceValue;
                else
                    score -= pieceValue;

                // if (color == PC_White && j <= 2) score -= 10;
                // else if (color == PC_Black && j >= 6) score -= 10;
            }
        }

        if (beingChecked(color))
            score -= 10000;
        if (beingChecked(PC_OTHER(color)))
            score += 10000;
        
        return score;
    }

    /**
     * Check if the given coordinates are valid inside the chessboard
     */
    inline bool validPos(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

    bool doMove(int x1, int y1, int x2, int y2, bool assumeLegal=false) {
        if (!assumeLegal && !moveLegal(x1, y1, x2, y2))
            return false;

        chessPiece &p1 = gameState[x1][y1];
        if (p1.color != currentPlayer)
            return false;

        if (p1.type == PT_King && x1 == 4 && ((y1 == 0 && y2 == 0) || (y1 == 7 && y2 == 7))
             && abs(x2-x1) == 2) {
            
            // castling, move rook to the right post
            if (x2 == 2 && gameState[0][y2].type == PT_Rook) {
                gameState[3][y2] = gameState[0][y2];
                gameState[0][y2] = empty;
            }
            else if (x2 == 6) {
                gameState[5][y2] = gameState[7][y2];
                gameState[7][y2] = empty;
            }
            else return false;
        }
        
        gameState[x2][y2] = gameState[x1][y1];
        gameState[x1][y1] = empty;

        currentPlayer = PC_OTHER(currentPlayer);
        return true;
    }

    bool beingChecked(pieceColor player) {
        int kx=-1, ky=-1;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (gameState[x][y].type == PT_King &&
                    gameState[x][y].color == player) {
                    kx = x;
                    ky = y;
                }
            }
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (gameState[x][y].color == PC_OTHER(player) &&
                    moveLegal(x, y, kx, ky, true)) 
                    return true;
            }
        }
        
        return false;
    }

    /**
     * Check whether the given move is valid according to the rules.
     * @param x1 x coordinate of where the piece is currently
     * @param y1 y coordinate of where the piece is currently
     * @param x2 x coordinate where the piece would be moved to
     * @param y2 y coordinate where the piece would be moved to
     * @return true if move is valid, false if not
     */
    bool moveLegal(int x1, int y1, int x2, int y2, bool inCheckTest=false) {
        const chessPiece &p1 = gameState[x1][y1];
        const chessPiece &p2 = gameState[x2][y2];

        if (!validPos(x1, y1) || !validPos(x2, y2) || p1.type == PT_Empty ||
            p1.color == p2.color)
            return false;
        
        switch (p1.type) {
        case PT_Pawn: {
            int ydiff = y2 - y1;

            // make sure the pawn does not move backwards 
            if ((p1.color == PC_White && ydiff <  1) ||
                (p1.color == PC_Black && ydiff > -1 ))
                return false;
                
            // pawn can't move more than two squares
            if ((p1.color == PC_White && ydiff >  2) ||
                (p1.color == PC_Black && ydiff < -2))
                return false;

            if (abs(ydiff) == 2) {
                if (p1.color == PC_White) {
                    if (y1 != 1 || gameState[x1][2].type != PT_Empty)
                        return false;
                }
                else if (p1.color == PC_Black) {
                    if (y1 != 6 || gameState[x1][5].type != PT_Empty)
                        return false;
                }

                if (p2.type != PT_Empty)
                    return false;
            }

            int xdiff = abs(x2 - x1);
            if (xdiff > 1) return false;

            // trying to attack another piece
            if (xdiff == 1 && p2.type == PT_Empty) 
                return false;
            else if (xdiff == 0 && p2.type != PT_Empty)
                return false;

            break;
        }

        case PT_Bishop: {
            if (abs(x2 - x1) != abs(y2 - y1))
                return false;
            
            int xd = x2 > x1 ? 1 : -1,
                yd = y2 > y1 ? 1 : -1;

            // see if we have another piece in the way
            int nsteps = abs(x2 - x1);
            
            for (int x=x1+xd, y=y1+yd, n=0; n < nsteps; n++, x+=xd, y+=yd) {
                // return false if we hit a pawn before the intended destination
                if (gameState[x][y].type != PT_Empty && (x != x2 || y != y2))
                    return false;
            }
            

            break;
        }

        case PT_Knight: {
            if ( ! ((abs(x2 - x1) == 1 && abs(y2 - y1) == 2) ||
                    (abs(x2 - x1) == 2 && abs(y2 - y1) == 1)) ) {
                return false;
            }
            break;
        }

        case PT_Rook: {
            if ( ((x1 == x2 && y1 != y2) || (x1 != x2 && y1 == y2)) == false )
                return false;

            int xd = 0, yd = 0;
            if (x1 != x2) xd = x2 > x1 ? 1 : -1;
            if (y1 != y2) yd = y2 > y1 ? 1 : -1;

            // see if we have another piece in the way
            int nsteps = 0;
            if (x2 != x1) nsteps = abs(x2 - x1);
            else nsteps = abs(y2 - y1);

            for (int x=x1+xd, y=y1+yd, n=0; n < nsteps; n++, x+=xd, y+=yd) {
                // printf("%d %d, %d %d, %d\n", x, y, x2, y2, gameState[x][y].type);
                
                // return false if we hit a pawn before the intended destination
                if (gameState[x][y].type != PT_Empty && (x != x2 || y != y2))
                    return false;
            }
            
            break;
        }

        case PT_Queen: 
        {
            // TODO should be refactored

            int xd = 0, yd = 0, nsteps = 0;
                
            if (abs(x2 - x1) == abs(y2 - y1))  {
                // diagonal move (like Bishop)
                xd = x2 > x1 ? 1 : -1;
                yd = y2 > y1 ? 1 : -1;

                nsteps = abs(x2 - x1);
            }
            else if ( (x1 == x2 && y1 != y2) || (x1 != x2 && y1 == y2) ) {
                // straight move (like Rook)
                    
                if (x1 != x2) xd = x2 > x1 ? 1 : -1;
                if (y1 != y2) yd = y2 > y1 ? 1 : -1;

                if (x2 != x1) nsteps = abs(x2 - x1);
                else nsteps = abs(y2 - y1);
            }
            else
                return false;
                
            // see if we have another piece in the way
            for (int x=x1+xd, y=y1+yd, n=0; n < nsteps; n++, x+=xd, y+=yd)
                // return false if we hit a pawn before the intended destination
                if (gameState[x][y].type != PT_Empty && (x != x2 || y != y2))
                    return false;
                
            break;
        }

        case PT_King:
            if (abs(y2 - y1) > 1)
                return false;
            
            if (abs(x2 - x1) > 1) {
                // check if castling
                if (x1 == 4 && (y1 == 0 || y1 == 7)) {
                    int xd = x2 > x1 ? 1 : -1;
                    if (x2 > x1) {
                        if (gameState[x1+1][y1].type != PT_Empty || gameState[x1+2][y1].type != PT_Empty
                            || gameState[x1+3][y1].type != PT_Rook)
                            return false;

                        if (!inCheckTest) {
                            chessBoard nb(*this);
                            nb.doMove(x1, y1, x1+1, y2, true);
                            if ( nb.beingChecked(p1.color))
                                return false;
                            nb = *this;
                            nb.doMove(x1, y1, x1+2, y2, true);
                            if (nb.beingChecked(p1.color))
                                return false;
                        }
                        
                    }
                    else {
                        if (gameState[x1-1][y1].type != PT_Empty || gameState[x1-2][y1].type != PT_Empty
                             || gameState[x1-3][y1].type != PT_Empty || gameState[x1-4][y1].type != PT_Rook)
                        return false;

                        if (!inCheckTest) {
                            chessBoard nb(*this);
                            if (nb.doMove(x1, y1, x1-1, y2, true) && nb.beingChecked(p1.color))
                                return false;
                            nb = *this;
                            if (nb.doMove(x1, y1, x1-2, y2, true) && nb.beingChecked(p1.color))
                                return false;
                        }
                        
                    }
                    
                }
                else
                    return false;
            }
            
            break;

        default: break;
        }

        if (!inCheckTest) {
            chessBoard nb(*this);
            nb.doMove(x1, y1, x2, y2, true);
            if (nb.beingChecked(p1.color))
                return false;
        }
        

        return true;
    }

    /**
     * Find ALL moves for the given player
     * @return list of moves
     */
    linkedList< cmove >* findMoves(pieceColor player) {
        linkedList< cmove >* moves = new linkedList< cmove >();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                chessPiece &piece = gameState[x][y];
                if (piece.color == player && piece.type != PT_Empty)
                    findMoves(*moves, player, x, y);
            }
        }

        return moves;
        
    }
    
    /**
     * Find moves for the piece in the given location
     * @param x row
     * @param y column 
     * @return list of moves
     */
    linkedList< cmove >* findMoves(int x, int y) {
        linkedList< cmove >* moves = new linkedList< cmove >();
        findMoves(*moves, currentPlayer, x, y);
        return moves;
    }
    
    int findMoves(linkedList< cmove > &moves, pieceColor player, int x, int y) {

        if (gameState[x][y].color != player) return 0;
        
        auto findMovesInDir = [this, &moves, player, x, y](int xdir, int ydir) {
            for (int tx=x+xdir, ty=y+ydir; moveLegal(x, y, tx, ty); tx+=xdir, ty+=ydir)
                moves.add( cmove( x, y, tx, ty ) );
        };

        switch (gameState[x][y].type) {
        case PT_Pawn:
        {
            int ydir = player == PC_White ? 1 : -1;

            // first move can go 2 squares
            if ( (player == PC_White && y == 1) ||
                 (player == PC_Black && y == 6) ) {
                    
                if (moveLegal(x, y, x, y + ydir*2))
                    moves.add( cmove( x, y, x, y + ydir*2 ) );
            }

            if (y+ydir >= 0 && y+ydir < 8) {
                if (moveLegal(x, y, x, y + ydir))
                    moves.add( cmove( x, y, x, y + ydir ) );

                if (moveLegal(x, y, x+1, y+ydir))
                    moves.add( cmove( x, y, x+1, y + ydir ) );

                if (moveLegal(x, y, x-1, y+ydir))
                    moves.add( cmove( x, y, x-1, y + ydir ) );
            }
            break;
        }

        case PT_Bishop: 
        {
            findMovesInDir( 1,  1);
            findMovesInDir( 1, -1);
            findMovesInDir(-1,  1);
            findMovesInDir(-1, -1);
            
            break;
        }

        case PT_Knight:
        {
            auto addMove = [this, &moves, x, y, player](int x2, int y2) {
                if (moveLegal(x,y,x2,y2)) {
                    chessPiece &p = gameState[x2][y2];
                    if (p.type == PT_Empty || p.color != player) {
                        moves.add( cmove(x, y, x2, y2) );
                    }
                }
            };
            
            addMove(x+1, y+2);
            addMove(x+1, y-2);

            addMove(x+2, y+1);
            addMove(x+2, y-1);

            addMove(x-1, y+2);
            addMove(x-1, y-2);

            addMove(x-2, y+1);
            addMove(x-2, y-1);
            
            break;
        }

        case PT_Rook:
        {
            findMovesInDir( 0,  1);
            findMovesInDir( 0, -1);
            findMovesInDir( 1,  0);
            findMovesInDir(-1,  0);
            break;
        }

        case PT_Queen:
            findMovesInDir( 1,  1);
            findMovesInDir( 1, -1);
            findMovesInDir(-1,  1);
            findMovesInDir(-1, -1);

            findMovesInDir( 0,  1);
            findMovesInDir( 0, -1);
            findMovesInDir( 1,  0);
            findMovesInDir(-1,  0);
            break;

        case PT_King:
        {
            auto addMove = [this, &moves, x, y, player](int xd, int yd) {
                int x2 = x + xd, y2 = y + yd;
                if (moveLegal(x,y,x2,y2)) {
                    chessPiece &p = gameState[x2][y2];
                    // TODO check that the king does not enter a place where it's checked!
                    if (p.type == PT_Empty || p.color != player) {
                        moves.add( cmove(x, y, x2, y2) );
                    }
                }
            };

            addMove( 0,  1);
            addMove( 1,  0);
            addMove( 0, -1);
            addMove(-1,  0);

            addMove(1,  1);
            addMove(1, -1);
            addMove(-1, 1);
            addMove(-1, -1);

            if (x == 4 && (y == 0 || y == 7)) {
                // try castling
                addMove( 2, 0);
                addMove(-2, 0);
            }
            
            break;
        }

        default: break;
        }

        return moves.size;
    }
    
    /**
     * Initial state of the chessboard
     */
    chessPiece startingState[8][8] = {
        {RookW,   PawnW, empty, empty, empty, empty, PawnB, RookB},
        {KnightW, PawnW, empty, empty, empty, empty, PawnB, KnightB},
        {BishopW, PawnW, empty, empty, empty, empty, PawnB, BishopB},
        {QueenW,  PawnW, empty, empty, empty, empty, PawnB, QueenB},
        {KingW,   PawnW, empty, empty, empty, empty, PawnB, KingB},
        {BishopW, PawnW, empty, empty, empty, empty, PawnB, BishopB},
        {KnightW, PawnW, empty, empty, empty, empty, PawnB, KnightB},
        {RookW,   PawnW, empty, empty, empty, empty, PawnB, RookB},
    };

    chessPiece testState2[8][8] = {
        {RookW, PawnW,   empty,   empty, empty,   BishopB, PawnB,   RookB},
        // {RookW, empty,   empty,   empty, empty,   BishopB, PawnB,   RookB},
        {empty, PawnW,   empty,   PawnB, empty,   KnightB, empty,   empty},
        {empty, PawnW,   KnightW, empty, empty,   empty,   PawnB,   empty},
        {empty, BishopW, empty,   empty, PawnW,   empty,   PawnB,   empty},
        {KingW, BishopW, empty,   PawnW, KnightW, PawnB,   QueenB,  KingB},
        {empty, PawnW,   QueenW,  empty, empty,   KnightB, PawnB,   empty},
        {empty, PawnW,   empty,   empty, empty,   PawnB,   BishopB, empty},
        {RookW, PawnW,   PawnB,   empty, empty,   empty,   empty,   RookB},
    };

    chessPiece testState[8][8] = {
        { empty,  empty, empty, empty, PawnB,   empty, empty, empty, },
        { QueenB, empty, empty, empty, empty,   empty, empty, empty, },
        { empty,  empty, empty, empty, empty,   PawnB, empty, empty, },
        { empty,  empty, empty, empty, BishopB, empty, KingW, empty, },
        { empty,  empty, empty, empty, empty,   empty, empty, empty, },
        { empty,  empty, empty, KingB, empty,   empty, empty, empty, },
        { empty,  empty, empty, empty, RookB,   empty, empty, empty, },
        { empty,  empty, empty, empty, empty,   empty, empty, empty, },
    };

    gameState_t gameState;
    pieceColor currentPlayer;
};


class moveNode {
public:
    pieceColor playerColor;
    chessBoard board;
    int value;
    cmove prev_move;
    // linkedList<cmove> *moves;

    // moveNode() : moves(0) {}
    moveNode()  {}
    
    moveNode(pieceColor c, chessBoard b, cmove m) :
        playerColor(c), board(b), prev_move(m) {

        value = b.evaluate(c);
        // moves = b.findMoves(c);
    }

    ~moveNode() {
        // if (moves) delete moves;
    }

    moveNode(pieceColor c, chessBoard b, int val) :
        playerColor(c), board(b), value(val) {
        // moves = b.findMoves(c);
    }
};
    
/**
 * Class containing the tree of possible moves, and the value of each
 * game state for the given player.
 */
class moveTree {
public:
    tree< moveNode* > *gameTree;

    moveTree(chessBoard &b, pieceColor color) {
        // static moveNode rootNode (color, b, cmove(-1,-1,-1,-1));
        // gameTree = new tree< moveNode >(rootNode);
        moveNode *rootNode = new moveNode(color, b, cmove(-1,-1,-1,-1));
        gameTree = new tree< moveNode* >(rootNode);
    }

    #if 1
    void freeNodes(tree< moveNode* > *ptr) {
        delete ptr->item;
        for (int i = 0; i < ptr->children->size; i++) {
            freeNodes((* ptr->children)[i]);
        }
    }
    
    ~moveTree() {
        if (gameTree) {
            freeNodes(gameTree);
            gameTree->free();
        }
    }
    #endif

    void buildTree(tree< moveNode* > *root, int depth) {
        if (depth <= 0) return;
        
        // linkedList<cmove> *moves = root->item->moves;
        linkedList<cmove> *moves = root->item->board.findMoves(root->item->board.currentPlayer);

        for (int i = 0; i < moves->size; i++) {
            chessBoard nb = root->item->board;
            cmove m = (*moves)[i];
            nb.doMove(m.from.x, m.from.y, m.to.x, m.to.y);

            moveNode *nn = new moveNode(PC_OTHER(nb.currentPlayer), nb, m);
            tree< moveNode* > *newnode = new tree< moveNode* >(nn);
            buildTree(newnode, depth-1);
            
            root->insert(newnode);
        }

        delete moves;
    }
};
