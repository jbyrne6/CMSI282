#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

char *KEY_NAMES[] = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
int NUMBER_OF_NOTES = 12;
int MAJOR_STEPS[] = {2,2,1,2,2,2};
int MINOR_STEPS[] = {2,1,2,2,1,2};

int indexOfNote(char* note) {
  int noteNum;
  for(int i = 0; i < NUMBER_OF_NOTES; i++) {
    if (strcmp(KEY_NAMES[i], note) == 0) {
      noteNum = i;
    }
  }
  return noteNum;
}

void print_scale(int note_index) {
  printf("%s major: %s ", KEY_NAMES[note_index], KEY_NAMES[note_index]);
  int offsetMajor = 0;
  for (int stepIndexMajor = 0; stepIndexMajor < 6; stepIndexMajor++) {
    offsetMajor += MAJOR_STEPS[stepIndexMajor];
    printf("%s ", KEY_NAMES[(note_index + offsetMajor) % NUMBER_OF_NOTES]);
  }
  printf("\n%s major: %s ", KEY_NAMES[note_index], KEY_NAMES[note_index]);
  int offsetMinor = 0;
  for (int stepIndexMinor = 0; stepIndexMinor < 6; stepIndexMinor++) {
    offsetMinor += MINOR_STEPS[stepIndexMinor];
    printf("%s ", KEY_NAMES[(note_index + offsetMinor) % NUMBER_OF_NOTES]);
  }
}

int main(int argc, char *argv[]) {
  print_scale(indexOfNote(argv[1]));
  return 0;
}
