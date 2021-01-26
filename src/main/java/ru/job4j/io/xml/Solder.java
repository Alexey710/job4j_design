package ru.job4j.io.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "solder")
@XmlAccessorType(XmlAccessType.FIELD)
public class Solder {
    @XmlAttribute
    private String rank;
    @XmlAttribute
    private int age;

    private Country country;
    private Rewards reward;

    public Solder() {
    }

    public Solder(String rank, int age, Country country, Rewards reward) {
        this.rank = rank;
        this.age = age;
        this.country = country;
        this.reward = reward;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Rewards getReward() {
        return reward;
    }

    public void setReward(Rewards reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "Solder{"
                + "rank='" + rank + '\''
                + ", age=" + age
                + ", country=" + country
                + ", rewards=" + reward
                + '}';
    }
}




