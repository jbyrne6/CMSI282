#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

char *KEY_NAMES[] = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
NUMBER_OF_NOTES = 12;
MAJOR_STEPS = {2,2,1,2,2,2};
MINOR_STEPS = {2,1,2,2,1,2};

boolean hasSharp(char* note) {
  if(strchr(note, '#') == NULL) {
    return false;
  } else {
    return true;
  }
}

int indexOfNote(char* note) {
  for(int i = 0; i < NUMBER_OF_NOTES; i++) {
    if (KEY_NAMES[i] == note) {
      return i;
    }
  }
}
char* stepThrough(char* note) {
  int noteLocation = strchr(KEY_NAMES,  )
}

int main() {
  printf("%s\n", joiner("too", "real"));
  return 0;
}
