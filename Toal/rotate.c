#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* theRotater( char* rString, int iterations ) {
  int inputLen = strlen(rString);
  int wrapAround = (iterations + inputLen)%inputLen;
  char* doubleString = malloc (inputLen*2 + 1);
  char* answer = malloc( inputLen + 1);

  strcpy(doubleString, rString);
  strcat(doubleString, rString);


  char* pointer = answer;
  char* doublePointer = doubleString;

  // memmove( doubleString, doubleString + wrapAround, inputLen);
  // strncat( answer, doubleString, inputLen);

  doublePointer += wrapAround;

  for( int x = 0; x < inputLen; x++){
    answer[x] = *doublePointer;
    doublePointer++;
    wrapAround++;
  }
  answer[inputLen] = '\0';

  return answer;

}

int main () {
  printf("%s\n", theRotater( "doghouse", 5));
  return 0;
}
