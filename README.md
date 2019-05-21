# Simple SpellChecker

### [SpellChecker](src/SpellChecker.java)
### [BK-Tree](src/BKTree.java)
### [Dictionary](src/Dictionary.java)


* Для выдачи ближайших слов использует структуру [BK-Tree](https://en.wikipedia.org/wiki/BK-tree), которая дает асимптотику O(_L * A * log n_),   где _L_ - лимит на ошибки (Не думаю, что по слову `abcdefg` нужно предлагать `congrats`, так что он уставлен на 3-ех),  _A_ - средняя длина слова в словаре, что обычно довольно маленькая величина.
* Реализован простенький Dictionary, состоящий из HashSet. Создается из txt файла по переданному в конструктор пути.
* Пример тестов ([Dictionary из тестов](input.txt)):
```java
Input:
  help
  input.txt
Output:
  Word is correct.
```
```java
Input:
  helt
  input.txt
Output:
  Word is incorrect, perhaps you mean:
  fell
  felt
  hell
  help
  smell
  halt
  shel
```
