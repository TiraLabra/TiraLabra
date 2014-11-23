
#pragma once

#include <list>
#include <map>
#include <utility>
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
    }

    /**
     * Prints out the textual representation of the chessboard.
     */
    void print() {
        cout << "\n---------------------------------\n";
        for (int i = 0; i < 8; i++) {
            cout << '|';
            for (int j = 0; j < 8; j++) {
                chessPiece &piece = gameState[i][j];
                cout << " ";
                if (piece.type == PT_Empty)
                    cout << "  |";
                else
                    cout << piece.chtype << " |";
            }
            cout << "\n---------------------------------\n";
        }
    }

    /**
     * Evaluates the worth of a given game state for the current player or the opponent.
     * @param color which player to evaluate position for
     * @return value of position
     */
    int evaluate(bool bmax) {
        pieceColor color;
        if (bmax) color = currentPlayer;
        else color = currentPlayer == PC_White ? PC_Black : PC_White;
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessPiece &piece = gameState[i][j];
                int pieceValue = piece.type * 10;
                if (piece.color == color)
                    score += pieceValue;
                else
                    score -= pieceValue;
                
            }
        }
        return score;
    }

    /**
     * Check if the given coordinates are valid inside the chessboard
     */
    inline bool validPos(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

    /**
     * Check whether the given move is valid according to the rules.
     * @param x1 x coordinate of where the piece is currently
     * @param y1 y coordinate of where the piece is currently
     * @param x2 x coordinate where the piece would be moved to
     * @param y2 y coordinate where the piece would be moved to
     * @return true if move is valid, false if not
     */
    bool moveLegal(int x1, int y1, int x2, int y2) {
        const chessPiece &p1 = gameState[x1][y1];
        const chessPiece &p2 = gameState[x2][y2];

        if (!validPos(x1, y1) || !validPos(x2, y2))
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
                (p1.color == PC_Black && ydiff < -2 ))
                return false;

            int xdiff = abs(x2 - x1);
            if (xdiff > 1) return false;

            // trying to attack another piece
            if (xdiff == 1 && p2.type == PT_Empty) 
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
            for (int x=x1, y=y1, n=0; n < nsteps; n++, x+=xd, y+=yd)
                // return false if we hit a pawn before the intended destination
                if (gameState[x][y].type != PT_Empty && x != x2 && y != y2)
                    return false;

            break;
        }

        case PT_Knight: {
            if ( ! ((abs(x2 - x1) == 1 && abs(y2 - y1) == 2) ||
                    (abs(x2 - x1) == 2 && abs(y2 - y1) == 1)) )
                return false;
            break;
        }

        case PT_Rook: {
            if ( ((x1 == x2 && y1 != y2) || (x1 != x2 && y1 != y2)) == false )
                return false;
                
            int xd = 0, yd = 0;
            if (x1 != x2) xd = x2 > x1 ? 1 : -1;
            if (y1 != y2) yd = y2 > y1 ? 1 : -1;

            // see if we have another piece in the way
            int nsteps = 0;
            if (x2 != x1) nsteps = abs(x2 - x1);
            else nsteps = abs(y2 - y1);

            for (int x=x1, y=y1, n=0; n < nsteps; n++, x+=xd, y+=yd)
                // return false if we hit a pawn before the intended destination
                if (gameState[x][y].type != PT_Empty && x != x2 && y != y2)
                    return false;
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
            else if ( (x1 == x2 && y1 != y2) || (x1 != x2 && y1 != y2) ) {
                // straight move (like Rook)
                    
                if (x1 != x2) xd = x2 > x1 ? 1 : -1;
                if (y1 != y2) yd = y2 > y1 ? 1 : -1;

                if (x2 != x1) nsteps = abs(x2 - x1);
                else nsteps = abs(y2 - y1);
            }
            else
                return false;
                
            // see if we have another piece in the way
            for (int x=x1, y=y1, n=0; n < nsteps; n++, x+=xd, y+=yd)
                // return false if we hit a pawn before the intended destination
                if (gameState[x][y].type != PT_Empty && x != x2 && y != y2)
                    return false;
                
            break;
        }

        case PT_King:
            if (abs(y2 - y1) > 1 || abs(x2 - x1) > 1)
                return false;
            break;

        default: break;
        }

        return true;
    }

    /**
     * Find moves for the piece in the given location
     * @param x row
     * @param y column 
     * @return list of moves
     */
    list< pair<int, int> > findMoves(int x, int y) {
        list< pair<int, int> > moves;
        switch (gameState[x][y].type) {
            case PT_Pawn:
                
                break;

            case PT_Bishop:
                break;

            case PT_Knight:
                break;

            case PT_Rook:
                break;

            case PT_Queen:
                break;

            case PT_King:
                break;

            default: break;
        }

        return moves;
    }
    
    chessPiece startingState[8][8] = {
        {RookW, KnightW, BishopW, QueenW, KingW, BishopW, KnightW, RookW},
        {PawnW, PawnW,   PawnW,   PawnW,  PawnW, PawnW,   PawnW,   PawnW},
        {empty, empty,   empty,   empty,  empty, empty,   empty,   empty},
        {empty, empty,   empty,   empty,  empty, empty,   empty,   empty},
        {empty, empty,   empty,   empty,  empty, empty,   empty,   empty},
        {empty, empty,   empty,   empty,  empty, empty,   empty,   empty},
        {PawnB, PawnB,   PawnB,   PawnB,  PawnB, PawnB,   PawnB,   PawnB},
        {RookB, KnightB, BishopB, QueenB, KingB, BishopB, KnightB, RookB},
    };

    gameState_t gameState;
    pieceColor currentPlayer;
};

/**
 * Class containing the tree of possible moves, and the value of each
 * game state for the given player.
 */
class moveTree {
public:
    pieceColor playerColor;
    chessBoard &board;
    list<moveTree> childNodes;
};
