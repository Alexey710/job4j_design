package ru.job4j.io.xml;

import com.sun.xml.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;

@XmlElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Rewards {
    private String[] rewardsArr;

    public Rewards() {
    }

    public Rewards(String[] rewardsArr) {
        this.rewardsArr = rewardsArr;
    }

    @javax.xml.bind.annotation.XmlElement(name = "rewards")
    public String[] getRewardsArr() {
        return rewardsArr;
    }

    public void setRewardsArr(String[] rewardsArr) {
        this.rewardsArr = rewardsArr;
    }

    @Override
    public String toString() {
        return "Rewards{"
                + "rewardsArr=" + Arrays.toString(rewardsArr)
                + '}';
    }
}
