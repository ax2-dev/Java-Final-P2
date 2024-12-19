class anaData {
    public static game findMostValuable(game[] games) {
        if (games == null || games.length == 0) return null;
        game mostValuable = games[0];
        for (game gameItem : games) {
            if (gameItem != null && gameItem.calcVal() > mostValuable.calcVal()) {
                mostValuable = gameItem;
            }
        }
        return mostValuable;
    }

    public static game searchByID(game[] games, String id) {
        quickSortByID(games, 0, games.length - 1);

        int resultIndex = binarySearchByID(games, id, 0, games.length - 1);
        return resultIndex == -1 ? null : games[resultIndex];
    }

    private static int binarySearchByID(game[] games, String id, int first, int last) {
        if (first > last) return -1;

        int mid = (first + last) / 2;
        if (games[mid] == null) return -1;

        int comparison = games[mid].id.compareTo(id);

        if (comparison == 0) return mid;
        else if (comparison > 0) return binarySearchByID(games, id, first, mid - 1);
        else return binarySearchByID(games, id, mid + 1, last);
    }

    public static void sortGamesByValue(game[] games) {
        quickSortByValue(games, 0, games.length - 1);
    }

    private static void quickSortByValue(game[] games, int low, int high) {
        if (low < high) {
            int pi = partitionByValue(games, low, high);

            quickSortByValue(games, low, pi - 1);
            quickSortByValue(games, pi + 1, high);
        }
    }

    private static int partitionByValue(game[] games, int low, int high) {
        game pivot = games[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (games[j] != null && games[j].calcVal() >= pivot.calcVal()) {
                i++;

                game temp = games[i];
                games[i] = games[j];
                games[j] = temp;
            }
        }

        game temp = games[i + 1];
        games[i + 1] = games[high];
        games[high] = temp;

        return i + 1;
    }

    private static void quickSortByID(game[] games, int low, int high) {
        if (low < high) {
            int pi = partitionByID(games, low, high);

            quickSortByID(games, low, pi - 1);
            quickSortByID(games, pi + 1, high);
        }
    }

    private static int partitionByID(game[] games, int low, int high) {
        game pivot = games[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (games[j] != null && games[j].id.compareTo(pivot.id) <= 0) {
                i++;

                game temp = games[i];
                games[i] = games[j];
                games[j] = temp;
            }
        }

        game temp = games[i + 1];
        games[i + 1] = games[high];
        games[high] = temp;

        return i + 1;
    }
}
