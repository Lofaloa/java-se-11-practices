#!/bin/python3

import re
import parse

pattern = "^\d{2}.\s{1}[A-Z](,\s*[A-Z])+\s+\[{1}CORRECT|ERROR\]{1}$"

def read_file_lines(path):
    with open(path, 'r') as file:
        return [line for line in file]

def parse_attempt(line):
    data = parse.parse("Attempt {} ({})", line)
    return { "id": data[0], "date": data[1]}

def read_attempts(path):
    lines = read_file_lines(path)
    attempts = []
    i = 0
    while i < len(lines):
        line = lines[i].strip()
        if line.startswith("Attempt"):
            attempts.append(parse_attempt(line))
            print(lines[i + 1])
            if re.match(pattern, lines[i + 1].strip()):
                print("Has at least one answer!")

        i += 1
    return attempts

def main():
    print(read_attempts("chapter01.txt"))

if __name__ == "__main__":
    main()
