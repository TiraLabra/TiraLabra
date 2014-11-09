
#pragma once

#include <list>
#include <map>
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
     * @param bmax true if evaluating for the current player (AI), false if for opponent
     * @return value of position
     */
    int evaluate(bool bmax) {
        // TODO implement!
        return 0;
    }

    /**
     * Prints out the textual representation of the chessboard.
     * @param player which player's moves to find
     * @return list of moves
     */
    list<chessBoard> findMoves(pieceColor player) {
        //TODO implement...
        list<chessBoard> moves;
        return moves;
    }
    
    chessPiece startingState[8][8] = {
        {RookB, KnightB, BishopB, QueenB, KingB, BishopB, KnightB, RookB},
        {PawnB, PawnB,   PawnB,   PawnB,  PawnB, PawnB,   PawnB,   PawnB},
        {empty, empty,   empty,   empty,  empty, empty,   empty,   empty},
        {empty, empty,   empty,   empty,  empty, empty,   empty,   empty},
        {empty, empty,   empty,   empty,  empty, empty,   empty,   empty},
        {empty, empty,   empty,   empty,  empty, empty,   empty,   empty},
        {PawnW, PawnW,   PawnW,   PawnW,  PawnW, PawnW,   PawnW,   PawnW},
        {RookW, KnightW, BishopW, QueenW, KingW, BishopW, KnightW, RookW},
    };

    gameState_t gameState;
};

/**
 * Class that containing the tree of possible moves, and the value of each
 * game state for the given player.
 */
class moveTree {
public:
    pieceColor playerColor;
    chessBoard &board;
    list<moveTree> childNodes;
};
