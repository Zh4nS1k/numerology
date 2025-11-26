package kz.narxoz.numerology.repository;

import kz.narxoz.numerology.model.NumerologyResult;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryNumerologyResultRepository implements NumerologyResultRepository {

    private final AtomicLong sequence = new AtomicLong();
    private final Map<Long, NumerologyResult> storage = new ConcurrentHashMap<>();

    @Override
    public NumerologyResult save(NumerologyResult result) {
        if (result.getId() == null) {
            result.setId(sequence.incrementAndGet());
        }
        storage.put(result.getId(), result);
        return result;
    }
}
