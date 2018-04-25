KEY_NAMES = ['A', 'A#', 'B', 'C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#']
STEPS = len(KEY_NAMES)
NUMBER_OF_KEYS = 88;
INITIAL_FREQUENCY = 27.5;

for i in range(NUMBER_OF_KEYS):
    key_name = KEY_NAMES[i % STEPS]
    frequency = INITIAL_FREQUENCY * (2.0 ** (i / STEPS))
    print(f'{key_name}\t{frequency:10.4f}')
