//package tictactoe
import kotlin.math.absoluteValue

fun main() {
    var spaces: Int
    var countOs: Int
    var countXs: Int
    var readAgain = true
    var xWinner = false
    var oWinner = false
    val cells = "         "
    val chars: CharArray = cells.toCharArray()
    println("---------")
    println("| ${chars[0]} ${chars[1]} ${chars[2]} |")
    println("| ${chars[3]} ${chars[4]} ${chars[5]} |")
    println("| ${chars[6]} ${chars[7]} ${chars[8]} |")
    println("---------")

    while (readAgain) {
        try {
            print("Enter the coordinates: ")
            // Read from console two integer base on a regular expression that can ignore spaces and tabs in the input string.
            val (rowCoordinate, columnCoordinate) = readLine()!!
                .trim().split("\\s+".toRegex()).map(String::toInt)
            // Confirms tha both coordinates are in the right range [1, 3]
            if ((rowCoordinate > 3 || rowCoordinate  < 1) ||
                (columnCoordinate > 3 || columnCoordinate  < 1)) {
                println("Coordinates should be from 1 to 3!") // out of range coordinate
                readAgain = true
                continue
            }
            // Converts the Ordered Pair Coordinates in one of the 9 position [0, 8] in a lineal vector
            val cellsIndex = if (rowCoordinate == 1) {columnCoordinate - rowCoordinate}
            else {if (rowCoordinate == 2) {columnCoordinate + rowCoordinate}
            else 5 + columnCoordinate}

            countXs = 0
            countOs = 0
            spaces = 0
            // set how many 'X', 'O' and spaces are in the board
            for (character in chars) {
                if ('X' == character) countXs++
                if ('O' == character) countOs++
                if (' ' == character) spaces++
            }
            // println("x: $countXs - o: $countOs - spaces: $spaces and position: $cellsIndex chars")
            // Confirm that the cell of the coordinates given is not fill
            if (('O' == chars[cellsIndex]) || ('X' == chars[cellsIndex])) {
                println("This cell is occupied! Choose another one!")
                readAgain = true
                continue
            } else {
                // set if the cell must be fill with an "X" or an "O"
                chars[cellsIndex] = when {
                    (countXs == countOs) -> 'X' else -> 'O'
                }
                // update the count of 'X', 'O' and spaces
                spaces--
                if (chars[cellsIndex] == 'X' )  countXs++ else countOs++

                // update the board
                println("---------")
                println("| ${chars[0]} ${chars[1]} ${chars[2]} |")
                println("| ${chars[3]} ${chars[4]} ${chars[5]} |")
                println("| ${chars[6]} ${chars[7]} ${chars[8]} |")
                println("---------")

                // test if 'X' is the winner
                if (('X' == chars[0] && 'X' == chars[1] && 'X' == chars[2]) ||
                    ('X' == chars[0] && 'X' == chars[3] && 'X' == chars[6]) ||
                    ('X' == chars[0] && 'X' == chars[4] && 'X' == chars[8]) ||
                    ('X' == chars[1] && 'X' == chars[4] && 'X' == chars[7]) ||
                    ('X' == chars[2] && 'X' == chars[4] && 'X' == chars[6]) ||
                    ('X' == chars[2] && 'X' == chars[5] && 'X' == chars[8]) ||
                    ('X' == chars[3] && 'X' == chars[4] && 'X' == chars[5]) ||
                    ('X' == chars[6] && 'X' == chars[7] && 'X' == chars[8])
                ) xWinner = true
                if (xWinner && !oWinner && ((countXs - countOs).absoluteValue < 2)) {
                    println("X wins")
                    readAgain = false
                }

                // test if 'O' is the winner
                if (('O' == chars[0] && 'O' == chars[1] && 'O' == chars[2]) ||
                    ('O' == chars[0] && 'O' == chars[3] && 'O' == chars[6]) ||
                    ('O' == chars[0] && 'O' == chars[4] && 'O' == chars[8]) ||
                    ('O' == chars[1] && 'O' == chars[4] && 'O' == chars[7]) ||
                    ('O' == chars[2] && 'O' == chars[4] && 'O' == chars[6]) ||
                    ('O' == chars[2] && 'O' == chars[5] && 'O' == chars[8]) ||
                    ('O' == chars[3] && 'O' == chars[4] && 'O' == chars[5]) ||
                    ('O' == chars[6] && 'O' == chars[7] && 'O' == chars[8])
                ) oWinner = true
                if (!xWinner && oWinner && ((countXs - countOs).absoluteValue < 2)) {
                    println("O wins")
                    readAgain = false
                }

                // exclude a possible inconsistency
                if ((xWinner && oWinner) || ((countXs - countOs).absoluteValue >= 2)) {
                    println("Impossible")
                    readAgain = false
                }
                // test if there is a draw
                if (!xWinner && !oWinner && (spaces == 0)) {
                    println("Draw")
                    readAgain = false
                }
            }
        } catch (NumberFormatException : Exception){
            println("You should enter numbers!") // Exception is handled!
            readAgain = true
        }
    }
}
