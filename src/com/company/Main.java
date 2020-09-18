package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {260, 250, 240, 270};
    public static int[] heroesDamage = {20, 25, 15, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            round();
        }
    }

    public static void chahgeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose - " + bossDefence);
    }

    public static void round() {
        chahgeBossDefence();
        heroesHits();
        bossHits();
        healingWarriors();
        printStatistics();

    }

    public static void healingWarriors (){
        int treatment = 30;
        Random r = new Random();
        int randomHeroes = r.nextInt(heroesHealth.length);
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[randomHeroes] < 100 && heroesHealth[3] > 0 &&
                    heroesHealth[randomHeroes] != heroesHealth[3] && heroesHealth[randomHeroes] > 0){
                heroesHealth[randomHeroes] += treatment;
                System.out.println("Medic has healed  - " + heroesAttackType[randomHeroes]);
                break;
            }
        }
    }


    public static void bossHits() {
        Random r = new Random();
        int chance = r.nextInt(3);
        double coeff = Math.random(); //какое-то число от 0 до 1 (н: 0.45)
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (chance == 0) {
                    if (heroesHealth[i] - (int) (bossDamage * coeff) < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - (int) (bossDamage * coeff);
                    }
                } else {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }
                }
            }
        }
        System.out.println("Boss became kind: " + (int) (bossDamage * coeff));
    }


    public static void heroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random r = new Random();
                    int coeff = r.nextInt(6) + 2;
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }

            }


        }
    }

    public static void printStatistics() {
        System.out.println("______________");
        System.out.println("Boss health: " + " " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health :" + " " + heroesHealth[i]);
        }
        System.out.println("______________");
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }

        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return  allHeroesDead;
    }
}
