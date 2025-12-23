package com.example.antipatterns;

import java.util.*;
import java.io.*;
import java.text.*;

// антипатерн: Interface Soup объединение нескольких интерфейсов в один монструозный, нарушая Interface Segregation Principle
interface ILibraryEverything {
    void addBook();
    void removeBook();
    void findBook();
    void updateBook();
    void addUser();
    void removeUser();
    void findUser();
    void updateUser();
    void borrowBook();
    void returnBook();
    void printStatistics();
    void printAllBooks();
    void printAllUsers();
    void generateReport();
}

// антипатерн: Висящие концы интерфейс с методами, которые бессмысленны и реализуются пустышками
interface ILegacyMiddleware {
    boolean checkSecurityToken();
    void logOperationToOldServer();
    void validateTransaction();
    int computeLegacyChecksum(String data);
}

// антипатерн: Stub  использование малоподходящего по смыслу интерфейса вместо создания нового
interface IBarcodeScannerDriver {
    void laserOn();
    void laserOff();
    String readData();
}

// антипатерн: Lasagna Code  использование большого количества уровней абстракции
abstract class Level1AbstractEntity {
    protected String id;
    public abstract String getId();
}

abstract class Level2AbstractNamedEntity extends Level1AbstractEntity {
    protected String name;
    public abstract String getName();
}

abstract class Level3AbstractDescribableEntity extends Level2AbstractNamedEntity {
    protected String description;
    public abstract String getDescription();
}

abstract class Level4AbstractTimestampedEntity extends Level3AbstractDescribableEntity {
    protected long timestamp;
    public abstract long getTimestamp();
}

abstract class Level5AbstractVersionedEntity extends Level4AbstractTimestampedEntity {
    protected int version;
    public abstract int getVersion();
}

abstract class Level6AbstractAuditableEntity extends Level5AbstractVersionedEntity {
    protected String createdBy;
    protected String modifiedBy;
    public abstract String getCreatedBy();
    public abstract String getModifiedBy();
}

// антипатерн: God Object  объект, который берет на себя слишком много функций и хранит все данные
class LibraryGodObject extends Level6AbstractAuditableEntity implements ILibraryEverything, ILegacyMiddleware, IBarcodeScannerDriver {
    // антипатерн: Hard Code  внедрение данных об окружении прямо в код
    private static final String admin_password = "aboba";
    // антипатерн: Magic Numbers константы без пояснения смысла
    private static final int magic_42 = 42;
    private static final int magic_7 = 7;
    private static final double magic_3_14 = 3.14159;
    private static final int magic_256 = 256;
    private static final int magic_1337 = 1337;
    private static final long magic_86400000 = 86400000L;
    // Все данные хранятся здесь - God Object
    private ArrayList<HashMap<String, Object>> books = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> users = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> borrowings = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> fines = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> logs = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> cache = new ArrayList<>();
    // антипатерн: Soft Code выносим в конфиг абсолютно всё, даже очевидные вещи
    private HashMap<String, String> config = new HashMap<>();
    private HashMap<String, String> messages = new HashMap<>();
    private HashMap<String, Integer> limits = new HashMap<>();
    private HashMap<String, Boolean> flags = new HashMap<>();
    private HashMap<String, Double> coefficients = new HashMap<>();
    private Scanner scanner;
    private int bookIdCounter = 0;
    private int userIdCounter = 0;
    private int loginAttempts = 0;
    private boolean isAuthenticated = false;
    // антипатерн: Soap Bubble объект с мусорными данными, притворяющийся полезным
    private HashMap<String, Object> bubbleCache = new HashMap<>();
    private ArrayList<String> bubbleHistory = new ArrayList<>();
    // антипатерн: Object Cesspool переиспользуемые грязные объекты
    private HashMap<String, Object> reusableBookObject = new HashMap<>();
    private HashMap<String, Object> reusableUserObject = new HashMap<>();
    private StringBuilder reusableStringBuilder = new StringBuilder();
    // антипатерн: Temporary Field поля которые имеют значение только в определённых ситуациях
    private int tempCalculationResult;
    private String tempSearchQuery;
    private HashMap<String, Object> tempFoundItem;
    private boolean tempOperationSuccess;
    // антипатерн: Lava Flow  устаревшие переменные которые страшно удалять
    @Deprecated private int oldBookCounter = 0;
    @Deprecated private String oldSystemStatus = "legacy";
    @Deprecated private ArrayList<Object> oldDataStorage = new ArrayList<>();

    public LibraryGodObject() {
        scanner = new Scanner(System.in);
        initializeSoftCodeConfig();
        initializeBubbleData();
        initializeLavaFlow();
        // Lasagna Code - используем все уровни наследования
        this.id = "god_001";
        this.name = "Library God Object";
        this.description = "This object does everything";
        this.timestamp = System.currentTimeMillis();
        this.version = 1;
        this.createdBy = "System";
        this.modifiedBy = "System";
    }
    // Lasagna Code - реализация абстрактных методов
    @Override public String getId() { return id; }
    @Override public String getName() { return name; }
    @Override public String getDescription() { return description; }
    @Override public long getTimestamp() { return timestamp; }
    @Override public int getVersion() { return version; }
    @Override public String getCreatedBy() { return createdBy; }
    @Override public String getModifiedBy() { return modifiedBy; }

    private void initializeSoftCodeConfig() {
        // Soft Code - даже очевидные вещи в конфиге
        config.put("greeting.message", "Добро пожаловать");
        config.put("goodbye.message", "До свидания");
        config.put("error.message", "Ошибка");
        config.put("success.message", "Успех");
        config.put("book.entity.name", "книга");
        config.put("user.entity.name", "пользователь");
        config.put("yes.word", "да");
        config.put("no.word", "нет");
        config.put("number.one", "1");
        config.put("number.two", "2");
        config.put("space.char", " ");
        config.put("newline.char", "\n");
        config.put("empty.string", "");
        messages.put("msg.001", "Введите название: ");
        messages.put("msg.002", "Введите автора: ");
        messages.put("msg.003", "Введите год: ");
        messages.put("msg.004", "Книга добавлена");
        messages.put("msg.005", "Книга не найдена");
        messages.put("msg.006", "Операция выполнена");
        messages.put("msg.007", "Доступ запрещён");
        limits.put("max.books", 1000);
        limits.put("max.users", 500);
        limits.put("max.borrowings", 5);
        limits.put("min.year", 1800);
        limits.put("max.year", 2025);
        limits.put("max.login.attempts", 3);
        flags.put("debug.mode", false);
        flags.put("verbose.mode", true);
        flags.put("safe.mode", true);
        flags.put("legacy.mode", true);
        coefficients.put("fine.per.day", 0.5);
        coefficients.put("tax.rate", 0.13);
        coefficients.put("discount.rate", 0.1);
    }

    // антипатерн: Soap Bubble - инициализация мусорных данных
    private void initializeBubbleData() {
        bubbleCache.put("init_time", System.currentTimeMillis());
        bubbleCache.put("fake_session", "SESSION_" + Math.random());
        bubbleCache.put("placeholder", new Object());
        bubbleHistory.add("System initialized");
        bubbleHistory.add("Cache warmed up");
        bubbleHistory.add("Ready for operations");
    }

    // антипатерн: Lava Flow - инициализация устаревшего кода
    private void initializeLavaFlow() {
        oldBookCounter = 0;
        oldSystemStatus = "initialized";
        oldDataStorage.add("legacy_data_1");
        oldDataStorage.add("legacy_data_2");
    }

    // антипатерн: Golden Hammer используем один подход HashMap для всего, даже где не нужно
    public boolean authenticate() {
        System.out.println("\nАвторизация");
        System.out.println("пароль - " + admin_password);
        // Golden Hammer - храним данные авторизации в HashMap вместо простых переменных
        HashMap<String, Object> authData = new HashMap<>();
        authData.put("attempts", 0);
        authData.put("maxAttempts", limits.get("max.login.attempts"));
        authData.put("authenticated", false);
        authData.put("timestamp", System.currentTimeMillis());
        while ((Integer)authData.get("attempts") < (Integer)authData.get("maxAttempts")) {
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();
            // антипатерн: Magic Numbers - используем магические числа добавляем искусственную проверку безопасности
            int securityHash = computeSecurityHash(password);
            // Hard Code - сравниваем с захардкоженным паролем
            if (password.equals(admin_password) && securityHash % magic_7 < magic_7) {
                authData.put("authenticated", true);
                isAuthenticated = true;
                // Висящие концы - вызываем бесполезную проверку
                checkSecurityToken();
                logOperationToOldServer();
                // Soap Bubble - записываем в мусорный кэш
                bubbleCache.put("last_login", new Date());
                bubbleCache.put("login_hash", securityHash);
                bubbleHistory.add("Login successful at " + new Date());
                System.out.println("Авторизация успешна!");
                // Lava Flow - обновляем устаревшие данные
                oldSystemStatus = "authenticated";
                // Stub - вызываем методы сканера
                laserOn();
                return true;
            } else {
                authData.put("attempts", (Integer)authData.get("attempts") + 1);
                loginAttempts++;
                int remaining = magic_42 / 14 - (Integer)authData.get("attempts");
                System.out.println("Неверный пароль! Осталось попыток: " + remaining);
                validateTransaction();
            }
        }
        System.out.println("Превышено количество попыток входа!");
        return false;
    }

    // антипатерн: Accidental Complexity излишне сложный расчёт хэша
    private int computeSecurityHash(String input) {
        int hash = magic_1337;
        for (int i = 0; i < input.length(); i++) {
            hash = ((hash * magic_42) + input.charAt(i)) % magic_256;
            hash ^= (int)(magic_3_14 * magic_7);
        }
        if (System.currentTimeMillis() % magic_86400000 > 0) {
            hash += magic_7;
        }
        return Math.abs(hash);
    }

    // антипатерн: Stub - библиотека реализует интерфейс сканера
    @Override
    public void laserOn() {
        System.out.println("Система запущена");
        config.put("system.status", "running");
        bubbleCache.put("laser_status", "on");
        bubbleHistory.add("Laser activated");
    }
    @Override
    public void laserOff() {
        System.out.println("Завершение работы системы");
        config.put("system.status", "stopped");
        bubbleCache.put("laser_status", "off");
        bubbleHistory.add("Laser deactivated");
        oldSystemStatus = "shotdown";
    }
    @Override
    public String readData() {
        // Stub - читаем данные со сканера которые на самом деле мусор
        String data = "scan " + System.currentTimeMillis();
        bubbleCache.put("last_scan", data);
        return data;
    }
    // антипатерн: Висящие концы - методы которые формально работают, но бессмысленны
    @Override
    public boolean checkSecurityToken() {
        tempOperationSuccess = true;
        bubbleHistory.add("Security check passed");
        return true;
    }
    @Override
    public void logOperationToOldServer() {
        String legacyLog = "log:" + System.currentTimeMillis() + ":";
        bubbleHistory.add(legacyLog);
        oldDataStorage.add(legacyLog);
    }

    @Override
    public void validateTransaction() {
        // Бессмысленная нагрузка, но влияет на временные поля
        int x = 0;
        for (int i = 0; i < magic_42 * 2; i++) {
            x += i % magic_7;
        }
        tempCalculationResult = x;
    }

    @Override
    public int computeLegacyChecksum(String data) {
        // Висящие концы - вычисляем контрольную сумму которая ни на что не влияет
        int checksum = 0;
        for (char c : data.toCharArray()) {
            checksum = (checksum + c * magic_7) % magic_256;
        }
        bubbleCache.put("last_checksum", checksum);
        return checksum;
    }

    // антипатерн: Copy and Paste Programming практически идентичный код для добавления книги и пользователя
    @Override
    public void addBook() {
        System.out.println("\nДобавление книги");
        if (!checkSecurityToken()) return;
        validateTransaction();
        System.out.print("Введите название книги: ");
        String title = scanner.nextLine();
        // Lava Flow - вызываем устаревший метод проверки
        performLegacyValidation(title);
        System.out.print("Введите автора книги: ");
        String author = scanner.nextLine();
        System.out.print("Введите год издания: ");
        String yearStr = scanner.nextLine();
        int year = 0;
        try {
            year = Integer.parseInt(yearStr);
        } catch (Exception e) {
            year = 2000;
        }
        System.out.print("Введите ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Введите жанр: ");
        String genre = scanner.nextLine();
        // Golden Hammer - всё храним в HashMap
        HashMap<String, Object> book = new HashMap<>();
        book.put("id", ++bookIdCounter);
        book.put("title", title);
        book.put("author", author);
        book.put("year", year);
        book.put("isbn", isbn);
        book.put("genre", genre);
        book.put("available", true);
        book.put("borrowedBy", null);
        book.put("borrowDate", null);
        book.put("rating", magic_42 / 10.0);
        book.put("cacheId", bubbleCache.get("fake_session"));
        books.add(book);
        // Висящие концы
        logOperationToOldServer();
        computeLegacyChecksum(title);
        // Lava Flow - обновляем устаревший счётчик
        oldBookCounter++;
        // Boat Anchor - вызываем будущую функцию
        futureFeatureBookIndexing(book);
        System.out.println("Книга успешно добавлена! ID: " + bookIdCounter);
    }

    // Copy-Paste: Практически идентичный код для добавления пользователя
    @Override
    public void addUser() {
        System.out.println("\nДобвление пользователя");
        if (!checkSecurityToken()) return;
        validateTransaction();
        System.out.print("Введите имя пользователя: ");
        String name = scanner.nextLine();
        performLegacyValidation(name);
        System.out.print("Введите фамилию пользователя: ");
        String surname = scanner.nextLine();
        System.out.print("Введите год рождения: ");
        String yearStr = scanner.nextLine();
        int year = 0;
        try {
            year = Integer.parseInt(yearStr);
        } catch (Exception e) {
            year = 2000;
        }
        System.out.print("Введите телефон: ");
        String phone = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        HashMap<String, Object> user = new HashMap<>();
        user.put("id", ++userIdCounter);
        user.put("name", name);
        user.put("surname", surname);
        user.put("year", year);
        user.put("phone", phone);
        user.put("email", email);
        user.put("active", true);
        user.put("borrowedBooks", new ArrayList<Integer>());
        user.put("registrationDate", new Date());
        user.put("borrowLimit", magic_7);
        user.put("sessionId", bubbleCache.get("fake_session"));
        users.add(user);
        logOperationToOldServer();
        computeLegacyChecksum(name + surname);
        futureFeatureUserNotification(user);
        System.out.println("Пользователь успешно добавлен! ID: " + userIdCounter);
    }
    // антипатерн: Lava Flow устаревший код который страшно удалять
    @Deprecated
    private void performLegacyValidation(String input) {
        // Старый код валидации
        int hash = 0;
        for (char c : input.toCharArray()) {
            hash += c * magic_7;
        }
        String unusedResult = "legace_hash " + hash;
        oldDataStorage.add(unusedResult);
        bubbleHistory.add("Legacy validation: " + unusedResult);
    }

    // антипатерн: Boat Anchor код на будущее
    private void futureFeatureBookIndexing(HashMap<String, Object> book) {
        // TODO: Когда-нибудь здесь будет индексация для поиска
        String indexKey = "IDX_" + book.get("id") + "_" + System.currentTimeMillis();
        bubbleCache.put(indexKey, book.get("title"));
        bubbleHistory.add("Book indexed (placeholder): " + indexKey);
    }

    private void futureFeatureUserNotification(HashMap<String, Object> user) {
        // TODO: Когда-нибудь здесь будет отправка уведомлений
        String notificationId = "NOTIF_" + user.get("id") + "_" + System.currentTimeMillis();
        bubbleCache.put(notificationId, "Welcome " + user.get("name"));
        bubbleHistory.add("Notification queued (placeholder): " + notificationId);
    }

    // антипатерн: Spaghetti Code запутанный код с множеством переходов и вложенных условий
    @Override
    public void borrowBook() {
        System.out.println("\nБронирование книги");
        // Висящие концы
        validateTransaction();
        int step = 1;
        int bookId = -1;
        int userId = -1;
        HashMap<String, Object> foundBook = null;
        HashMap<String, Object> foundUser = null;
        String errorMessage = "";
        // Спагетти-код с множеством шагов
        while (step <= 10) {
            if (step == 1) {
                System.out.print("Введите ID книги: ");
                try {
                    bookId = Integer.parseInt(scanner.nextLine());
                    if (bookId < 0 || bookId > magic_1337) {
                        errorMessage = "ID вне допустимого диапазона";
                        step = 8;
                    } else {
                        step = 2;
                    }
                } catch (Exception e) {
                    errorMessage = "Неверный формат ID книги";
                    step = 8;
                }
            } else if (step == 2) {
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i) != null) {
                        if (books.get(i).get("id") != null) {
                            if ((Integer)books.get(i).get("id") == bookId) {
                                foundBook = books.get(i);
                                tempFoundItem = foundBook;
                                break;
                            }
                        }
                    }
                }
                if (foundBook != null) {
                    step = 3;
                } else {
                    errorMessage = "Книга не найдена";
                    step = 8;
                }
            } else if (step == 3) {
                if (foundBook.get("available") != null) {
                    if ((Boolean)foundBook.get("available") == true) {
                        step = 4;
                    } else {
                        errorMessage = "Книга уже забронирована";
                        step = 8;
                    }
                } else {
                    step = 4;
                }
            } else if (step == 4) {
                System.out.print("Введите ID пользователя: ");
                try {
                    userId = Integer.parseInt(scanner.nextLine());
                    step = 5;
                } catch (Exception e) {
                    errorMessage = "Неверный формат ID пользователя";
                    step = 8;
                }
            } else if (step == 5) {
                for (int j = 0; j < users.size(); j++) {
                    if (users.get(j) != null) {
                        if (users.get(j).get("id") != null) {
                            if ((Integer)users.get(j).get("id") == userId) {
                                foundUser = users.get(j);
                                break;
                            }
                        }
                    }
                }
                if (foundUser != null) {
                    step = 6;
                } else {
                    errorMessage = "Пользователь не найден";
                    step = 8;
                }
            } else if (step == 6) {
                @SuppressWarnings("unchecked")
                ArrayList<Integer> currentBooks = (ArrayList<Integer>)foundUser.get("borrowedBooks");
                int limit = foundUser.get("borrowLimit") != null ?
                        (Integer)foundUser.get("borrowLimit") : magic_7;
                if (currentBooks != null && currentBooks.size() >= limit) {
                    errorMessage = "Превышен лимит книг (макс: " + limit + ")";
                    step = 8;
                } else if (foundUser.get("active") != null && !(Boolean)foundUser.get("active")) {
                    errorMessage = "Пользователь неактивен";
                    step = 8;
                } else {
                    step = 7;
                }
            } else if (step == 7) {
                foundBook.put("available", false);
                foundBook.put("borrowedBy", userId);
                foundBook.put("borrowDate", new Date());
                @SuppressWarnings("unchecked")
                ArrayList<Integer> borrowedBooks = (ArrayList<Integer>)foundUser.get("borrowedBooks");
                if (borrowedBooks == null) {
                    borrowedBooks = new ArrayList<>();
                }
                borrowedBooks.add(bookId);
                foundUser.put("borrowedBooks", borrowedBooks);
                HashMap<String, Object> borrowing = new HashMap<>();
                borrowing.put("bookId", bookId);
                borrowing.put("userId", userId);
                borrowing.put("borrowDate", new Date());
                borrowing.put("returnDate", null);
                borrowing.put("dueDate", new Date(System.currentTimeMillis() + magic_86400000 * magic_7));
                borrowings.add(borrowing);
                logOperationToOldServer();
                bubbleHistory.add("Borrow: book " + bookId + " to user " + userId);
                tempOperationSuccess = true;
                step = 9;
            } else if (step == 8) {
                System.out.println("ошибка: " + errorMessage);
                tempOperationSuccess = false;
                step = 11;
            } else if (step == 9) {
                System.out.println("Книга успешно забронирована!");
                step = 11;
            } else {
                step = 11;
            }
        }
    }
    // антипатерн: Accidental Complexity слишком сложное решение для простой задачи
    @Override
    public void returnBook() {
        System.out.println("\nВозврат книги");
        System.out.print("Введите ID книги для возврата: ");
        String input = scanner.nextLine();
        // Излишне сложная валидация ввода Object Cesspool - используем грязный StringBuilder
        reusableStringBuilder.setLength(0);
        reusableStringBuilder.append("processing:");
        StringBuilder cleanInput = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9') {
                cleanInput.append(c);
            }
        }
        reusableStringBuilder.append(cleanInput.toString());
        int bookId;
        try {
            bookId = Integer.parseInt(cleanInput.toString());
        } catch (Exception e) {
            System.out.println("Ошибка: неверный ID");
            return;
        }
        // Излишне сложный поиск через Iterator
        HashMap<String, Object> targetBook = null;
        Iterator<HashMap<String, Object>> bookIterator = books.iterator();
        while (bookIterator.hasNext()) {
            HashMap<String, Object> currentBook = bookIterator.next();
            if (currentBook != null) {
                Object idObj = currentBook.get("id");
                if (idObj != null) {
                    if (idObj instanceof Integer) {
                        Integer currentId = (Integer) idObj;
                        if (currentId.intValue() == bookId) {
                            targetBook = currentBook;
                            tempFoundItem = targetBook;
                            break;
                        }
                    }
                }
            }
        }
        if (targetBook == null) {
            System.out.println("Книга не найдена");
            return;
        }
        // Излишне сложная проверка доступности
        Object availableObj = targetBook.get("available");
        boolean isAvailable = false;
        if (availableObj != null) {
            if (availableObj instanceof Boolean) {
                isAvailable = ((Boolean) availableObj).booleanValue();
            }
        }
        if (isAvailable) {
            System.out.println("Эта книга не была забронирована");
            return;
        }
        Object borrowDateObj = targetBook.get("borrowDate");
        if (borrowDateObj != null && borrowDateObj instanceof Date) {
            long borrowTime = ((Date) borrowDateObj).getTime();
            long currentTime = System.currentTimeMillis();
            long daysOverdue = (currentTime - borrowTime) / magic_86400000 - magic_7;
            if (daysOverdue > 0) {
                double fine = daysOverdue * coefficients.get("fine.per.day") * magic_3_14;
                System.out.printf("Штраф за просрочку: %.2f руб.\n", fine);
                HashMap<String, Object> fineRecord = new HashMap<>();
                fineRecord.put("bookId", bookId);
                fineRecord.put("amount", fine);
                fineRecord.put("date", new Date());
                fines.add(fineRecord);
            }
        }
        targetBook.put("available", Boolean.valueOf(true));
        // Излишне сложное обновление пользователя
        Object borrowedByObj = targetBook.get("borrowedBy");
        if (borrowedByObj != null && borrowedByObj instanceof Integer) {
            Integer borrowedById = (Integer) borrowedByObj;
            for (int userIndex = 0; userIndex < users.size(); userIndex++) {
                HashMap<String, Object> user = users.get(userIndex);
                if (user != null) {
                    Object userIdObj = user.get("id");
                    if (userIdObj != null && userIdObj instanceof Integer) {
                        if (((Integer) userIdObj).equals(borrowedById)) {
                            @SuppressWarnings("unchecked")
                            ArrayList<Integer> borrowedBooks = (ArrayList<Integer>) user.get("borrowedBooks");
                            if (borrowedBooks != null) {
                                borrowedBooks.remove(Integer.valueOf(bookId));
                            }
                            break;
                        }
                    }
                }
            }
        }
        targetBook.put("borrowedBy", null);
        targetBook.put("borrowDate", null);
        for (HashMap<String, Object> borrowing : borrowings) {
            if (borrowing.get("bookId") != null &&
                    borrowing.get("bookId").equals(bookId) &&
                    borrowing.get("returnDate") == null) {
                borrowing.put("returnDate", new Date());
                break;
            }
        }
        logOperationToOldServer();
        bubbleHistory.add("Return: book " + bookId);
        System.out.println("Книга успешно возвращена!");
    }

    // антипатерн: Reinventing the Wheel собственная сортировка вместо Collections.sort
    private void myOwnBubbleSort(ArrayList<HashMap<String, Object>> list, String key) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Object val1 = list.get(j).get(key);
                Object val2 = list.get(j + 1).get(key);
                boolean shouldSwap = false;
                if (val1 instanceof String && val2 instanceof String) {
                    if (((String) val1).compareTo((String) val2) > 0) {
                        shouldSwap = true;
                    }
                } else if (val1 instanceof Integer && val2 instanceof Integer) {
                    if ((Integer) val1 > (Integer) val2) {
                        shouldSwap = true;
                    }
                }
                if (shouldSwap) {
                    HashMap<String, Object> temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        bubbleHistory.add("Sorted " + n + " items by " + key);
    }

    // антипатерн: Reinventing the Square Wheel плохая реализация поиска подстроки
    private boolean myOwnContains(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return false;
        }
        if (needle.length() == 0) {
            return true;
        }
        if (needle.length() > haystack.length()) {
            return false;
        }
        // O(n*m) сложность вместо оптимальных алгоритмов
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            boolean found = true;
            for (int j = 0; j < needle.length(); j++) {
                char h = haystack.charAt(i + j);
                char n = needle.charAt(j);
                // Плохой способ привести к нижнему регистру
                if (h >= 'A' && h <= 'Z') h = (char)(h + magic_42 - 10);
                if (n >= 'A' && n <= 'Z') n = (char)(n + magic_42 - 10);
                if (h != n) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void findBook() {
        System.out.println("\nПоиск книги");
        System.out.print("Введите поисковый запрос: ");
        String query = scanner.nextLine();
        tempSearchQuery = query;
        if (bubbleCache.containsKey("search_" + query)) {
            System.out.println("(Результат из кэша)");
        }
        bubbleCache.put("search_" + query, System.currentTimeMillis());
        ArrayList<HashMap<String, Object>> results = new ArrayList<>();
        for (HashMap<String, Object> book : books) {
            String title = (String) book.get("title");
            String author = (String) book.get("author");
            String genre = (String) book.get("genre");
            // Используем квадратный велосипед
            if (myOwnContains(title, query) ||
                    myOwnContains(author, query) ||
                    myOwnContains(genre, query)) {
                results.add(book);
            }
        }
        // Используем велосипед для сортировки
        myOwnBubbleSort(results, "title");
        if (results.isEmpty()) {
            System.out.println("Книги не найдены");
        } else {
            System.out.println("Найдено книг: " + results.size());
            for (HashMap<String, Object> book : results) {
                pBI(book);
            }
        }
        logOperationToOldServer();
    }

    // антипатерн: Cryptic Code непонятные аббревиатуры в названиях
    private void pBI(HashMap<String, Object> b) {
        // pBI = printBookInfo
        int i = (Integer) b.get("id");
        String t = (String) b.get("title");
        String a = (String) b.get("author");
        int y = (Integer) b.get("year");
        boolean av = (Boolean) b.get("available");
        String s = av ? "Доступна" : "Забронирована";
        String r = b.get("rating") != null ?
                String.format("%.1f", (Double)b.get("rating")) :
                String.valueOf(magic_42 / 10.0);
        System.out.println("[" + i + "] " + t + " - " + a + " (" + y + ") - " + s + " | Рейтинг: " + r);
    }

    // антипатерн: Programming by Permutation пробуем разные комбинации не понимая какая правильная
    @Override
    public void findUser() {
        System.out.println("\nПоиск пользователя");
        System.out.print("Введите имя или фамилию: ");
        String query = scanner.nextLine();
        tempSearchQuery = query;
        ArrayList<HashMap<String, Object>> results = new ArrayList<>();
        for (HashMap<String, Object> user : users) {
            String name = (String) user.get("name");
            String surname = (String) user.get("surname");
            boolean match = false;
            // Попытка 1 - точное совпадение имени
            if (name != null && name.equals(query)) match = true;
            // Попытка 2 - точное совпадение фамилии
            if (!match && surname != null && surname.equals(query)) match = true;
            // Попытка 3 - без учёта регистра имя
            if (!match && name != null && name.equalsIgnoreCase(query)) match = true;
            // Попытка 4 - без учёта регистра фамилия
            if (!match && surname != null && surname.equalsIgnoreCase(query)) match = true;
            // Попытка 5 - содержит в имени
            if (!match && name != null && name.contains(query)) match = true;
            // Попытка 6 - содержит в фамилии
            if (!match && surname != null && surname.contains(query)) match = true;
            // Попытка 7 - toLowerCase имя
            if (!match && name != null && name.toLowerCase().contains(query.toLowerCase())) match = true;
            // Попытка 8 - toLowerCase фамилия
            if (!match && surname != null && surname.toLowerCase().contains(query.toLowerCase())) match = true;
            // Попытка 9 - полное имя
            if (!match && name != null && surname != null) {
                String fullName = name + " " + surname;
                if (fullName.toLowerCase().contains(query.toLowerCase())) match = true;
            }
            // Попытка 10 - полное имя наоборот
            if (!match && name != null && surname != null) {
                String fullName = surname + " " + name;
                if (fullName.toLowerCase().contains(query.toLowerCase())) match = true;
            }
            // Попытка 11 - наш квадратный велосипед
            if (!match && name != null && myOwnContains(name, query)) match = true;
            // Попытка 12 - наш квадратный велосипед для фамилии
            if (!match && surname != null && myOwnContains(surname, query)) match = true;
            if (match) {
                results.add(user);
            }
        }
        if (results.isEmpty()) {
            System.out.println("Пользователи не найдены");
        } else {
            System.out.println("Найдено пользователей: " + results.size());
            for (HashMap<String, Object> user : results) {
                pUI(user);
            }
        }
        bubbleHistory.add("User search: " + query + ", found: " + results.size());
    }

    // Cryptic Code - pUI = printUserInfo
    private void pUI(HashMap<String, Object> u) {
        System.out.println("[" + u.get("id") + "] " +
                u.get("name") + " " + u.get("surname") +
                " | Тел: " + u.get("phone") +
                " | Email: " + u.get("email") +
                " | Лимит книг: " + (u.get("borrowLimit") != null ? u.get("borrowLimit") : magic_7));
    }

    // антипатерн: Blind Faith никаких проверок
    @Override
    public void removeBook() {
        System.out.println("\nУдаление книги");
        System.out.print("Введите ID книги для удаления: ");
        // Blind Faith - не проверяем ввод
        int bookId = Integer.parseInt(scanner.nextLine());
        // Blind Faith - уверены что книга есть
        HashMap<String, Object> bookToRemove = null;
        for (HashMap<String, Object> book : books) {
            if ((Integer)book.get("id") == bookId) {
                bookToRemove = book;
                break;
            }
        }
        // Blind Faith - не проверяем нашли ли
        books.remove(bookToRemove);
        logOperationToOldServer();
        oldBookCounter--;
        System.out.println("Книга удалена!");
    }

    @Override
    public void removeUser() {
        System.out.println("\nУдаление пользователя");
        System.out.print("Введите ID пользователя: ");
        // Blind Faith
        int userId = Integer.parseInt(scanner.nextLine());
        HashMap<String, Object> userToRemove = null;
        for (HashMap<String, Object> user : users) {
            if ((Integer)user.get("id") == userId) {
                userToRemove = user;
                break;
            }
        }
        users.remove(userToRemove);
        logOperationToOldServer();
        System.out.println("Пользователь удалён!");
    }
    // антипатерн: Object Cesspool переиспользование грязных объектов
    @Override
    public void updateBook() {
        System.out.println("\nОбновление книги");
        System.out.print("Введите ID книги: ");
        try {
            int bookId = Integer.parseInt(scanner.nextLine());
            HashMap<String, Object> bookToUpdate = null;
            for (HashMap<String, Object> book : books) {
                if (book.get("id") != null && (Integer)book.get("id") == bookId) {
                    bookToUpdate = book;
                    tempFoundItem = book;
                    break;
                }
            }
            if (bookToUpdate == null) {
                System.out.println("Книга не найдена");
                return;
            }
            // Object Cesspool - используем грязный объект, в нём остались данные от предыдущих операций
            reusableBookObject.put("dirty_data", "left_from_previous_operation");
            reusableBookObject.put("old_id", magic_1337);
            reusableBookObject.put("garbage", new Object());
            System.out.print("Новое название (Enter - оставить): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                reusableBookObject.put("title", newTitle);
                bookToUpdate.put("title", reusableBookObject.get("title"));
            }
            System.out.print("Новый автор (Enter - оставить): ");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isEmpty()) {
                reusableBookObject.put("author", newAuthor);
                bookToUpdate.put("author", reusableBookObject.get("author"));
            }
            System.out.print("Новый год (Enter - оставить): ");
            String newYear = scanner.nextLine();
            if (!newYear.isEmpty()) {
                try {
                    reusableBookObject.put("year", Integer.parseInt(newYear));
                    bookToUpdate.put("year", reusableBookObject.get("year"));
                } catch (Exception e) {
                    // Blind Faith - игнорируем ошибку
                }
            }
            logOperationToOldServer();
            bubbleHistory.add("Book updated: " + bookId);
            System.out.println("Книга обновлена!");
        } catch (Exception e) {
            System.out.println("Ошибка при обновлении");
        }
    }

    @Override
    public void updateUser() {
        System.out.println("\nОбновление пользователя");
        System.out.print("Введите ID пользователя: ");
        try {
            int userId = Integer.parseInt(scanner.nextLine());
            HashMap<String, Object> userToUpdate = null;
            for (HashMap<String, Object> user : users) {
                if (user.get("id") != null && (Integer)user.get("id") == userId) {
                    userToUpdate = user;
                    tempFoundItem = user;
                    break;
                }
            }
            if (userToUpdate == null) {
                System.out.println("Пользователь не найден");
                return;
            }
            // Object Cesspool - грязный объект
            reusableUserObject.put("garbage", "from_last_time");
            reusableUserObject.put("zombie_data", new Object());
            reusableUserObject.put("old_timestamp", System.currentTimeMillis() - magic_86400000);
            System.out.print("Новый телефон (Enter - оставить): ");
            String newPhone = scanner.nextLine();
            if (!newPhone.isEmpty()) {
                reusableUserObject.put("phone", newPhone);
                userToUpdate.put("phone", reusableUserObject.get("phone"));
            }
            System.out.print("Новый email (Enter - оставить): ");
            String newEmail = scanner.nextLine();
            if (!newEmail.isEmpty()) {
                reusableUserObject.put("email", newEmail);
                userToUpdate.put("email", reusableUserObject.get("email"));
            }
            logOperationToOldServer();
            bubbleHistory.add("User updated: " + userId);
            System.out.println("Пользователь обновлён!");
        } catch (Exception e) {
            System.out.println("Ошибка при обновлении");
        }
    }

    // антипатерн: Ravioli Code методы тесно связаны друг с другом как пельмени
    @Override
    public void printStatistics() {
        System.out.println("\nСтатистика библиотеки");
        // Ravioli - цепочка вызовов зависимых методов
        int totalBooks = calcTB();
        int availableBooks = calcAB(totalBooks);
        int borrowedBooks = calcBB(totalBooks, availableBooks);
        double borrowRate = calcBR(borrowedBooks, totalBooks);
        String status = detLS(borrowRate, totalBooks);
        String recommendation = genRec(status, borrowRate);
        int fineTotal = calcTF();
        pSR(totalBooks, availableBooks, borrowedBooks, borrowRate, status, recommendation, fineTotal);
        bubbleHistory.add("Statistics viewed");
    }

    // Cryptic Code calcTB = calculateTotalBooks
    private int calcTB() {
        return books.size();
    }
    // Cryptic Code calcAB = calculateAvailableBooks
    private int calcAB(int total) {
        int count = 0;
        for (HashMap<String, Object> book : books) {
            if (book.get("available") != null && (Boolean)book.get("available")) {
                count++;
            }
        }
        return count;
    }

    // Cryptic Code calcBB = calculateBorrowedBooks
    private int calcBB(int total, int available) {
        return total - available;
    }

    // Cryptic Code calcBR = calculateBorrowRate
    private double calcBR(int borrowed, int total) {
        if (total == 0) return 0;
        return (double) borrowed / total * (magic_42 * 2 + 16);
    }

    // Cryptic Code detLS = determineLibraryStatus
    private String detLS(double rate, int total) {
        if (total == 0) return "пустая";
        if (rate > magic_42 * 2 - 4) return "высокая загрузка";
        if (rate > magic_42 + 8) return "средняя загрузка";
        return "низкая загрузка";
    }
    // Cryptic Code genRec = generateRecommendation
    private String genRec(String status, double rate) {
        if (status.equals("пустая")) return "Добавьте книги!";
        if (status.equals("высокая загрузка")) return "Закупите больше экземпляров";
        if (status.equals("средняя загрузка")) return "Всё в порядке";
        return "Проведите маркетинговую кампанию";
    }
    // Cryptic Code calcTF = calculateTotalFines
    private int calcTF() {
        double total = 0;
        for (HashMap<String, Object> fine : fines) {
            if (fine.get("amount") != null) {
                total += (Double) fine.get("amount");
            }
        }
        return (int) total;
    }

    // Cryptic Code pSR = printStatisticsReport; антипатерн: Long Parameter List
    private void pSR(int t, int a, int b, double r, String s, String rec, int f) {
        System.out.println("Всего книг: " + t);
        System.out.println("Доступно: " + a);
        System.out.println("Забронировано: " + b);
        System.out.printf("Процент загрузки: %.2f%%\n", r);
        System.out.println("Статус: " + s);
        System.out.println("Рекомендация: " + rec);
        System.out.println("Всего пользователей: " + users.size());
        System.out.println("Всего штрафов: " + f + " руб.");
        System.out.println("Записей в истории: " + bubbleHistory.size());
    }

    @Override
    public void printAllBooks() {
        System.out.println("\nВсе книги");
        if (books.isEmpty()) {
            System.out.println("Библиотека пуста");
            return;
        }
        myOwnBubbleSort(books, "title");
        for (HashMap<String, Object> book : books) {
            pBI(book);
        }
        logOperationToOldServer();
    }

    @Override
    public void printAllUsers() {
        System.out.println("\nВсе пользователи");
        if (users.isEmpty()) {
            System.out.println("Пользователей нет");
            return;
        }
        for (HashMap<String, Object> user : users) {
            pUI(user);
        }
        logOperationToOldServer();
    }

    // антипатерн: Two Tunnel Отдельная система отчётов вместо расширения статистики
    @Override
    public void generateReport() {
        System.out.println("\nОтчёт");
        // Вместо расширения printStatistics создаём дублирующий функционал
        System.out.println("Дата отчёта: " + new Date());
        System.out.println("ID системы: " + getId());
        System.out.println("Версия: " + getVersion());
        System.out.println("Книг в системе: " + books.size());
        System.out.println("Пользователей: " + users.size());
        System.out.println("Активных бронирований: " + cntAB());
        System.out.println("Общая сумма штрафов: " + calcTF() + " руб.");
        bubbleHistory.add("Report generated");
        logOperationToOldServer();
    }

    // Cryptic Code cntAB = countActiveBorrowings
    private int cntAB() {
        int count = 0;
        for (HashMap<String, Object> b : borrowings) {
            if (b.get("returnDate") == null) count++;
        }
        return count;
    }

    // антипатерн: Dead Code код который никогда не вызывается
    private void neverCalledMethod() {
        System.out.println("код никогда не выполнится");
        int x = magic_42 + magic_7;
        String s = "dead";
        for (int i = 0; i < magic_256; i++) {
            x += i;
        }
    }
    public void showMenu() {
        while (true) {
            System.out.println("1.  Добавить книгу");
            System.out.println("2.  Удалить книгу");
            System.out.println("3.  Найти книгу");
            System.out.println("4.  Обновить книгу");
            System.out.println("5.  Все книги");
            System.out.println("6.  Добавить пользователя");
            System.out.println("7.  Удалить пользователя");
            System.out.println("8.  Найти пользователя");
            System.out.println("9.  Обновить пользователя");
            System.out.println("10. Все пользователи");
            System.out.println("11. Забронировать книгу");
            System.out.println("12. Вернуть книгу");
            System.out.println("13. Статистика");
            System.out.println("14. Отчёт");
            System.out.println("15. История операций");
            System.out.println("0.  Выход");
            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine();
            checkSecurityToken();
            switch (choice) {
                case "1": addBook(); break;
                case "2": removeBook(); break;
                case "3": findBook(); break;
                case "4": updateBook(); break;
                case "5": printAllBooks(); break;
                case "6": addUser(); break;
                case "7": removeUser(); break;
                case "8": findUser(); break;
                case "9": updateUser(); break;
                case "10": printAllUsers(); break;
                case "11": borrowBook(); break;
                case "12": returnBook(); break;
                case "13": printStatistics(); break;
                case "14": generateReport(); break;
                case "15": showHistory(); break;
                case "0":
                    laserOff();
                    System.out.println(config.get("goodbye.message"));
                    return;
                default:
                    System.out.println("сделайте правильный выбор");
            }
        }
    }
    private void showHistory() {
        System.out.println("\nИстория операций");
        int count = 0;
        for (String entry : bubbleHistory) {
            if (count >= magic_42 / 2) {
                System.out.println("... и ещё " + (bubbleHistory.size() - count) + " записей");
                break;
            }
            System.out.println("- " + entry);
            count++;
        }
    }
}

public class LibraryAntiPatterns {
    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // Blind Faith - игнорируем
        }
        LibraryGodObject library = new LibraryGodObject();
        if (library.authenticate()) {
            library.showMenu();
        } else {
            System.out.println("Доступ запрещён!");
        }
    }
}