#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* theRotater( char* rString, int iterations ) {
  int inputLen = strlen(rString);
  int wrapAround = (iterations + inputLen)%inputLen + 1;
  char* doubleString = malloc (inputLen*2 + 1);
  char* answer = malloc( inputLen + 1);

  strcpy(doubleString, rString);
  strcat(doubleString, rString);


  char* pointer = answer;
  char* doublePointer = doubleString;
  doublePointer += wrapAround;
  //printf("%s\n", doublePointer);

  for( int x = 0; x < inputLen; x++){
    answer[x] = *doublePointer;
    doublePointer++;
    wrapAround++;
  }

  return answer;

}

int main () {
  // printf("%s\n", theRotater( "doghouse", 3));
  printf("%s\n", theRotater( "doghouse", 3));
  return 0;
}
