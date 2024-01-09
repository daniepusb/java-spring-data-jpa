package com.pdaniel.pizza.persistence.audit;

import com.pdaniel.pizza.persistence.entity.Pizza;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private Pizza currentValue;
    private static final Logger log = LogManager.getLogger(AuditPizzaListener.class);

    @PostLoad
    public void postLoad(Pizza pizza) {
        log.info("POST LOAD");
        this.currentValue = SerializationUtils.clone(pizza);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(Pizza pizza) {
        log.info("POST PERSIST OR UPDATE");
        log.info("OLD Pizza: {}", this.currentValue);
        log.info("NEW Pizza: {}", pizza);
    }

    @PreRemove
    public void onPreDelete(Pizza pizza) {
        log.info("{}",pizza);
    }
    
}
