# This C99 application takes a single command line argument which is the name of
# a note and prints, to standard output, the major (Ionian) and minor (Aeolian)
# scales starting at that note.  The program is very weak; note names are
# constrained to be in {A, A#, B, C, C#, D, D#, E, F, F#, G, G#}. You should
# totally enhance this to use flats properly, and even double sharps and double
# flats.

import sys

KEY_NAMES = ['A', 'A#', 'B', 'C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#']
NUMBER_OF_NOTES = len(KEY_NAMES)

MAJOR_STEPS = [2,2,1,2,2,2]
MINOR_STEPS = [2,1,2,2,1,2]
NUMBER_OF_STEPS = len(MAJOR_STEPS)

def print_scale(note_index, type, steps):
    print(f'{KEY_NAMES[note_index]:3}{type}: {KEY_NAMES[note_index]:3}', end='')
    offset = 0
    for step in steps:
        offset += step
        print(f'{KEY_NAMES[(note_index + offset) % NUMBER_OF_NOTES]:3}', end='')
    print()

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("This program requires exactly one command line argument\n")
        sys.exit(1)

    try:
        key_index = KEY_NAMES.index(sys.argv[1].upper())
    except ValueError:
        print(f'No such key: {sys.argv[1]}')
        sys.exit(2)

    print_scale(key_index, "major", MAJOR_STEPS)
    print_scale(key_index, "minor", MINOR_STEPS)
