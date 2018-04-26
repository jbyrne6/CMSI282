#include <stdio.h>
#include <string.h>

char* theRotater( char* rString, int i ) {
  int l = strlen(rString);
  int wrapAround = (i + l)%l + 1;
  char doubleString[l + l + 1]
  char answer[l + 1];

  strcat(doubleString, rString);
  strcat(doubleString, rString);

  for( int x = i; x < l; x++){
    strcat(answer, doubleString[x]);
  }

  return answer;

}

int main () {
  printf("%s\n", theRotater( "doghouse", 3));
  return 0;
}
