# Hangman Game 🎮

## 📌 Descriere
Joc clasic Spânzurătoarea implementat în Java cu interfață grafică Swing. Ghicește cuvântul literă cu literă înainte să fie desenată spânzurătoarea completă. Cuvintele sunt din domeniul IT, iar jocul include niveluri de dificultate, sistem de scor și tastatură vizuală interactivă.

## 🚀 Funcționalități
- Interfață grafică completă cu Java Swing
- Spânzurătoare desenată progresiv pas cu pas (`paintComponent`)
- Tastatură vizuală A–Z cu feedback vizual (verde = corect, roșu = greșit)
- 3 niveluri de dificultate: Ușor (8 încercări), Mediu (6), Greu (4)
- Sistem de scor acumulat pe parcursul sesiunii
- 20 de cuvinte din domeniul IT, selectate aleatoriu
- Buton „Joc nou" pentru restart rapid

## 🛠️ Tehnologii
- Java 16+
- Java Swing (`JFrame`, `JPanel`, `JButton`, `Graphics2D`)
- `paintComponent` pentru desenul spânzurătorii
- `SwingUtilities.invokeLater` pentru thread safety

## ▶️ Cum se rulează
1. Clonează repository-ul
```bash
git clone https://github.com/username/hangman-game.git
cd hangman-game
```
2. Compilează proiectul
```bash
javac Hangman.java
```
3. Rulează jocul
```bash
java Hangman
```

> **Cerințe:** Java 16 sau mai nou instalat pe sistem.

## 📷 Capturi de ecran
<img src="capturi_de_ecran.png" width="400"/>

## 💡 Ce am învățat
- **Java Swing & GUI**: Am construit o interfață grafică completă folosind `JFrame`, `JPanel`, `JButton` și layout managers (`BorderLayout`, `BoxLayout`, `GridLayout`) pentru a organiza componentele vizual.
- **Desenare cu `Graphics2D`**: Am implementat metoda `paintComponent` pentru a desena spânzurătoarea progresiv în funcție de numărul de greșeli, folosind `BasicStroke`, `drawLine` și `drawOval` cu antialiasing activat.
- **Gestionarea stării jocului**: Am menținut starea completă a jocului (cuvânt, litere ghicite, greșeli) și am sincronizat-o cu interfața grafică la fiecare acțiune a utilizatorului.
- **Colecții Java**: Am folosit `Set<Character>` (`LinkedHashSet`) pentru a stoca literele greșite fără duplicate și în ordinea introducerii.
- **Thread safety în Swing**: Am folosit `SwingUtilities.invokeLater` pentru a lansa aplicația corect pe Event Dispatch Thread, conform bunelor practici Swing.
- **Modularitate**: Am separat logica jocului de desenare printr-un inner class (`HangmanPanel`), menținând codul organizat și ușor de extins.
