package project.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookstore.entity.Slider;
import project.bookstore.exception.SliderNotFoundException;
import project.bookstore.repository.SliderRepository;

import java.util.List;
import java.util.Optional;

@Service

public class SliderService {

    @Autowired
    private SliderRepository sliderRepository;

    public List<Slider> getAllSlides() {
        return sliderRepository.findAll();
    }

    public Slider save(Slider slider) {
        sliderRepository.save(slider);
        return slider;
    }

    public List<Slider> getSelectedSlider() {
        return sliderRepository.getSelectedSliders();
    }

    public Slider get(Integer id) throws SliderNotFoundException {
        Optional<Slider> result = sliderRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }
        throw new SliderNotFoundException("Could not find any slider with ID " + id);
    }

    public void delete(Integer id) throws SliderNotFoundException {
        Long count = sliderRepository.countById(id);
        if (count == null || count == 0) {
            throw new SliderNotFoundException("Could not find any slider with ID " + id);
        }
        sliderRepository.deleteById(id);
    }
}
