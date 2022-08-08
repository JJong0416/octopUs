package com.octopus.mail;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StrategyFactory {

    private Map<StrategyName, SendStrategy> strategyMap;

    StrategyFactory(Set<SendStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public SendStrategy findStrategy(StrategyName strategyName) {
        return strategyMap.get(strategyName);
    }

    private void createStrategy(Set<SendStrategy> strategySet) {
        strategyMap = new HashMap<StrategyName, SendStrategy>();
        strategySet.forEach(
                strategy -> strategyMap.put(strategy.getStrategyName(), strategy));
    }

}
