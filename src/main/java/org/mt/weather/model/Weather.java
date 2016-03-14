package org.mt.weather.model;

import com.google.common.base.MoreObjects;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {

    private int pressure;
    private int humidity;

    @Indexed
    private int tempMin;

    @Indexed
    private int tempMax;

    private Condition condition;

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("pressure", pressure)
                .add("humidity", humidity)
                .add("tempMin", tempMin)
                .add("tempMax", tempMax)
                .add("condition", condition)
                .toString();
    }
}
