# Rare Game Store Thing

This project is a Java app designed to analyze input list of video games (both text and binary). It performs operations such as reading the files, using sorting algorithms to figure out the most expensive games, and making me cry.

---

## Features


1. **Algorithms**:
   - **Quick Sort** was chosen for sorting games by value. I used QuickSort because its quick (duh) and is good for handling large data sets.
        - Found in `anaData.java`
   - **Binary Search** was chosen because when searching for IDs it can be expected that they would already be sorted (usually done by a system that automatically increments entries IE: SQL AUTOINCREMENT). Because we are just processing the data into a different format I thought it would be optimal to use for memory efficency. (Listing 11.6 was used as reference)
        - Can be found in `anaData.java` 


2. **Custom Exceptions**:
  - `InvalidGameFormatExcept`: Ensures data integrity for CSV and binary files.
  - `DupeGameExcept`: Handles duplicate game entries.

3. **Duplicate Detection**:
   - **HashSet** is used to detect and prevent duplicate game entries. This is used over other systems because of its efficency (at first I used an array that tracked the previously processed IDs but that was an inefficent pain)
        - Can be found in `utils.java`

4. **CSV Reading**
    - Reads retro game data, validates format, and handles duplicate IDs using manual checks.
    - Will throw `InvalidGameFormatExcept` and `DupeGameExcept` when their respective error occurs. It will check to make sure the proper amount of columns exist in the CSV file.
        - Can be found in `utils.java`
5. **Binary (File) Reading**
    - In order to accomplish this I used FileInputStream and ObjectInputStream. It would grab the objects from the binary file and then interpret them into the data. (Listings 10.5-10.8)
        - Can be found in `utils.java`
6. **Sister Program**
    - I made a program to go along with this program. Its used to generate files that either work with the program, or purposefully trigger an error. I did this because I'm lazy and didn't want to keep having to create files for testing.
        - Found in `writeBinFile.java`
7. **Abstract Method**
    - I made calcVal() abstract because the instructions asked me to.
        - Can be found in `game.java`
8. **Dynamic Array Sizing**
   - The games array is initially created with a size of 5. As the array is populated the arrays size will be increased to fit the rest of the data. It will double the arrays size if the current amount of entries is the same as the total entries the array allows then it will double the size.
      - Found in `utils.java`
---


### Notes

This project is very scuffed by does work and does check the requirements.
