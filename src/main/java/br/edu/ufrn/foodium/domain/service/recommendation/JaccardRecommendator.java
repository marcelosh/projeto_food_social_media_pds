package br.edu.ufrn.foodium.domain.service.recommendation;

import br.edu.ufrn.foodium.domain.model.Tag;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class JaccardRecommendator implements Recommendator{
    @Override
    public float recommend(TagRecommendable source, TagRecommendable target) {
        float index = calculateIndex(source, target);
        return index;
    }
    
    private Float calculateIndex(TagRecommendable source, TagRecommendable target) {
        float index = 0f;
        int union = 0;
        int intersection = 0;

        List<Tag> intersectionTags = new ArrayList<>();

        for (Tag sourceTag : source.getRecommendableTags()) {
            for (Tag targetTag : target.getRecommendableTags()) {
                if(sourceTag.getId().equals(targetTag.getId())) {
                    intersectionTags.add(sourceTag);
                    break;
                }
            }
        }

        intersection = intersectionTags.size();
        union = source.getRecommendableTags().size() + target.getRecommendableTags().size() - intersection;
        index = ((float) intersection) / ((float) union);

        return index;
    }
}
