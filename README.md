1. Класс Horse:
конструктор
Проверить, что при передаче в конструктор первым параметром null, будет выброшено IllegalArgumentException. Для этого нужно воспользоваться методом assertThrows;
Проверить, что при передаче в конструктор первым параметром null, выброшенное исключение будет содержать сообщение "Name cannot be null.". Для этого нужно получить сообщение из перехваченного исключения и воспользоваться методом assertEquals;
Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы (пробел, табуляция и т.д.), будет выброшено IllegalArgumentException. Чтобы выполнить проверку с разными вариантами пробельных символов, нужно сделать тест параметризованным;
Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы (пробел, табуляция и т.д.), выброшенное исключение будет содержать сообщение "Name cannot be blank.";
Проверить, что при передаче в конструктор вторым параметром отрицательного числа, будет выброшено IllegalArgumentException;
Проверить, что при передаче в конструктор вторым параметром отрицательного числа, выброшенное исключение будет содержать сообщение "Speed cannot be negative.";
Проверить, что при передаче в конструктор третьим параметром отрицательного числа, будет выброшено IllegalArgumentException;
Проверить, что при передаче в конструктор третьим параметром отрицательного числа, выброшенное исключение будет содержать сообщение "Distance cannot be negative.";
метод getName
Проверить, что метод возвращает строку, которая была передана первым параметром в конструктор;
метод getSpeed
Проверить, что метод возвращает число, которое было передано вторым параметром в конструктор;
метод getDistance
Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор;
Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами;
метод move
Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9. Для этого нужно использовать MockedStatic и его метод verify;
Проверить, что метод присваивает дистанции значение высчитанное по формуле: distance + speed * getRandomDouble(0.2, 0.9). Для этого нужно замокать getRandomDouble, чтобы он возвращал определенные значения, которые нужно задать параметризовав тест.
2. Класс Hippodrome:
Конструктор
Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException;
Проверить, что при передаче в конструктор null, выброшенное исключение будет содержать сообщение "Horses cannot be null.";
Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException;
Проверить, что при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение "Horses cannot be empty.";
метод getHorses
Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор. При создании объекта Hippodrome передай в конструктор список из 30 разных лошадей;
метод move
Проверить, что метод вызывает метод move у всех лошадей. При создании объекта Hippodrome передай в конструктор список из 50 моков лошадей и воспользуйся методом verify.
метод getWinner
Проверить, что метод возвращает лошадь с самым большим значением distance.
3. Класс Main
метод main
Проверить, что метод выполняется не дольше 22 секунд. Для этого воспользуйся аннотацией Timeout. После написания этого теста, отключи его (воспользуйся аннотацией Disabled). Так он не будет занимать время при запуске всех тестов, а при необходимости его можно будет запустить вручную.
Что нужно логировать
1. Класс Main:
После создания объекта ипподрома, добавить в лог запись вида: 2022-05-31 17:05:26,152 INFO Main: Начало скачек. Количество участников: 7
После вывода информации о победители, добавить в лог запись вида: 2022-05-31 17:05:46,963 INFO Main: Окончание скачек. Победитель: Вишня
Класс Hippodrome:
Если в конструктор был передан null, то перед пробросом исключения, добавить в лог запись вида: 2022-05-31 17:29:30,029 ERROR Hippodrome: Horses list is null
b. Если в конструктор был передан пустой список, то перед пробросом исключения, добавить в лог запись вида: 2022-05-31 17:30:41,074 ERROR Hippodrome: Horses list is empty
В конце конструктора добавить в лог запись вида: 2022-05-31 17:05:26,152 DEBUG Hippodrome: Создание Hippodrome, лошадей [7]
3. Класс Horse:
Если в конструктор вместо имени передан null, то перед пробросом исключения, добавить в лог запись вида: 2022-05-31 17:34:59,483 ERROR Horse: Name is null
Если переданное в конструктор имя пустое, то перед пробросом исключения, добавить в лог запись вида: 2022-05-31 17:36:44,196 ERROR Horse: Name is blank
Если переданная в конструктор скорость меньше нуля, то перед пробросом исключения, добавить в лог запись вида:2022-05-31 17:40:27,267 ERROR Horse: Speed is negative
Если переданная в конструктор дистанция меньше нуля, то перед пробросом исключения, добавить в лог запись вида: 2022-05-31 17:41:21,938 ERROR Horse: Distance is negative
В конце конструктора добавить в лог запись вида: 2022-05-31 17:15:25,842 DEBUG Horse: Создание Horse, имя [Лобстер], скорость [2.8]
Логи должны писаться в файл hippodrome.log, который должен располагаться в корне проекта в папке logs. Каждый день файл должен переименовываться по шаблону в hippodrome.2021-12-31.log и вместо него должен создаваться новый hippodrome.log. Для этого используй аппендер RollingFile. При этом файлы старше 7 дней, должны удаляться.
