package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
    Functia este apelata cu array-ul A, nrOfElements care reprezinta cate elemente din array am nevoie pentru a obtine acea suma,
    pos reprezinta pozitia in array la care caut elementul de scazut din suma initiala, iar sum reprezinta suma intiala pe care o
    caut in array.
     */
    public static boolean canBeSplit(ArrayList<Integer> array,int nrOfElements,int pos,int sum){
        /*
        Aceasta este functia pentru a afla daca un array poate fi divizat in 2 array-uri cu media elementelor egala.
        Modul in care am ales sa rezolv aceasta problema este prin apel recursiv.
        Mergand prin apeluri recursive in variabila sum voi avea suma ramasa pentru a gasi elementele
        care dau suma initiala, cu care a fost facut primul apel al functiei.

        Prima clauza if reprezinta cazul de succes in care suma initiala a ajuns la 0 si am gasit atatea elemente cate trebuiau
        pentru a avea suma initiala.
         */
        if(sum == 0 && nrOfElements == 0) {
            return true;
        }
        /*
        Aceasta clauza if reprezinta cazul in care array-ul nu poate fi impartit, iar acest lucru se intampla
        daca au fost parcurse toate elementele array-ului si suma este diferita de 0 (nu intra pe prima clauza) sau
        daca numarul de elmente ramase pentru a construi suma initiala a ajuns la 0 iar suma este diferita de 0,
        ceea ce demonstreaza ca inca mai aveam nevoie de elemente din array pentru a avea suma initiala.
         */
        if(pos == array.size() || nrOfElements == 0){
            return false;
        }
        /*
        In aceasta clauza if se face apelul recursiv in caz ca din suma ramasa in acest moment poate fi scazut elementul curent,
        iar apelul recursiv este facut pe 2 cazuri, al doilea caz (discardElement) fiind facut dupa ce se
        intoarce din primul apel recursiv, neadaugand la suma elementul curent. Rezultatul final este obtinut
        prin operatia de OR dintre cele 2 apeluri recursive, deoarece daca cel putin unul din ele returneaza
        "true', inseamna ca a putut fi gasita acea suma cu numarul de elemente, pozitia si suma cu care a fost facut apelul.
         */
        if(sum-array.get(pos) >= 0){
            boolean takeElement = canBeSplit(array, nrOfElements - 1, pos + 1, sum - array.get(pos));
            boolean discardElement = canBeSplit(array, nrOfElements, pos + 1, sum);
            return takeElement || discardElement;
        }
        /*
        pe ultima clauza intra in caz ca diferenta dintre suma ramasa si elementul din pozitia curenta din array este mai mica decat 0,
        acel lucru insemnand ca elementul curent nu este unul bun.
         */
        return canBeSplit(array, nrOfElements, pos + 1, sum);

    }

    public static boolean splitArray(ArrayList<Integer> a){
        /*
        Prima data verific daca cumva array-ul are size 2, in acest fel acel array poate fi impartit in 2 array-uri cu medii egale
        doar daca cele 2 elemente sunt egale
         */
        if(a.size() == 2){
            return a.get(0).equals(a.get(1));
        }
        /* Pentru a putea imparti array-ul in 2 array-uri cu media egala,
         conform formulei demonstrate in documentatie acea valoare medie este egala
         cu media array-ului
         */
        int totalSum = 0; // calculam suma tuturor elementelor pentru a afla pe urma valoarea medie a array-ului
        for(Integer i: a){
            totalSum+=i;
        }
        float avg = (float)totalSum/a.size(); //valoarea medie a array-ului

        /*
        In aceasta ultima parte parcurg array-ul pana la lungimea sa /2 + 1, deoarece este destul sa verific
        daca pot forma suma S1 avand a.size()/2+1 elemente, deoarece de exemplu daca am un array cu 7 elemente
        si nu pot avea suma S1 pentru ca S1/nrElemente = avg, pentru nrElemente cu 1,2,3 sau 4, sigur nu voi avea cu 5 sau 6
        elemente deoarece nu se verifica proprietatea de avg1 = avg2, unde avg1 si avg2 reprezinta media pe primul, repsectiv al
        doilea array obtinute prin impartirea array-ului A.

        Verificarea din clauza if este facuta deoarece daca suma pe acel array obtinut cu elemente din array-ul A,
        nu este un numar intreg, sigur nu pot fi gasite k elemente in array care sa dea acea suma.
         */
        for(int i = 1;i<a.size()/2 +1;i++) {
            if (avg * i == Math.ceil(avg * i)) {
                if(canBeSplit(a,i,0,(int)avg*i)){ //functia este apelata cu array-ul A, i care reprezinta
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //testarea solutiei
        ArrayList<Integer> a = new ArrayList<>(List.of(1,2,3,4,5,6,7,8));
        System.out.println(splitArray(a));
    }


}
