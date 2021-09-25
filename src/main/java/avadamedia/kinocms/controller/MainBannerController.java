//package avadamedia.kinocms.controller;
//
//import avadamedia.kinocms.model.Movie;
//import avadamedia.kinocms.model.banners.MainBanner;
//import avadamedia.kinocms.service.MainBannerService;
//import avadamedia.kinocms.service.MovieService;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("admin/banners")
//@AllArgsConstructor(onConstructor = @__(@Autowired))
//public class MainBannerController {
//
//    private final MainBannerService service;
//
//    //    Show all Movies
//    @GetMapping({"/", ""})
//    public ModelAndView movies() {
//        return new ModelAndView("/admin/banners/list", "movies", service.getAllMainBanners());
//    }
//
//    //    Add part mapping of MainBanner
//    @GetMapping("/add")
//    public String addMovie(Model model) {
//        model.addAttribute("main", new MainBanner());
//        return "/admin/banners/add";
//    }
//
//    @PostMapping("/add")
//    public String addMovie(Movie movie) {
//        service.createMovie(movie);
//        return "redirect:/admin/movies";
//    }
//
//    //    Delete part mapping of Movies
//    @GetMapping("/delete/{id}")
//    public String deleteMovie(@PathVariable("id") Long id) {
//        service.deleteMovieById(id);
//        return "redirect:/admin/movies";
//    }
//
//    //    Update part mapping of Movies
//    @GetMapping("/update/{id}")
//    public ModelAndView updateMovie(@PathVariable("id") Long id) {
//        return new ModelAndView("/admin/movies/update", "movie", service.getMovieById(id));
//    }
//
//    //    @PostMapping("/update")
//    @PutMapping("/update")
//    public String updateMovie(Movie movie) {
//        service.updateMovie(movie);
//        return "redirect:/admin/movies";
//    }
//
//}