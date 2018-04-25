#include <stdio.h>
#include <string.h>
#include <math.h>

int main() {
  char *KEY_NAMES[] = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
  int STEPS = sizeof( KEY_NAMES ) / sizeof( KEY_NAMES[0] );
  int INITIAL_FREQUENCY = 27.5;
  int NUMBER_OF_KEYS = 88;

  for (int i = 0; i < NUMBER_OF_KEYS; i++) {
    char *key_name = KEY_NAMES[i % STEPS];
    float frequency = INITIAL_FREQUENCY * ( pow( 2.0, (float)i / (float)STEPS ) ) ;
    //printf("i:  %d\n", i);
    //printf("STEPS:  %d\n", STEPS);
    //printf("i/STEPS:  %10.4f\n", ((float)i / (float)STEPS));
    printf("%s    %10.4f\n", key_name, frequency);
  }
  return 0;
}
