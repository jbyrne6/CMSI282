#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int modifiedWordLen (char* inputWord) {
  int modifiedWordLen = 0;
  for (int i = 1; i <= strlen(inputWord); i++) {
    modifiedWordLen += i;
  }
  return modifiedWordLen;
}

char* joiner(char* firstString, char* secondString) {
  char* result = malloc(modifiedWordLen(firstString) + modifiedWordLen(secondString) + 1);
  char* target = result;
  int counter = 1;

  //loop for first word
  for (int i = 0; i < strlen(firstString); i++) {
    for (int j = 0; j < counter; j++) {
      *target = firstString[j];
      target++;
    }
    counter++;
  }

  //loop for second word
  counter = strlen(secondString);
  for (int m = 0; m < strlen(secondString); m++) {
    for (int n = 0; n < counter; n++) {
      *target = secondString[n];
      target++;
    }
    counter--;
  }

  *target = '\0';
  return result;
  //free(result);
}

int main() {
  printf("%s\n", joiner("talking", "heads"));
  //printf("%d", modifiedWordLen("too"));
  //printf("%d", modifiedWordLen("real"));
  return 0;
}
