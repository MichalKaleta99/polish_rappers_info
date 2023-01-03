package com.example.RappersInfo.demo.rapperdatabase;



import com.example.RappersInfo.demo.rapperdatabase.db.AlbumRepository;
import com.example.RappersInfo.demo.rapperdatabase.db.InstagramAccountRepository;
import com.example.RappersInfo.demo.rapperdatabase.db.RapperRepository;
import com.example.RappersInfo.demo.rapperdatabase.info.Album;
import com.example.RappersInfo.demo.rapperdatabase.info.InstagramAccount;
import com.example.RappersInfo.demo.rapperdatabase.info.Rapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private RapperRepository rapperRepository;
    @Autowired
    private InstagramAccountRepository instagramAccountRepository;
    @Autowired
    private AlbumRepository albumRepository;
    List<Rapper> rappersList;
    List<InstagramAccount> rappersWithInstagramsList = new ArrayList<>();
    List<Album> albumList = new ArrayList<>();
    Rapper askedRapper;
    String rapperName;
    int numberOfAlbums;

    public Controller(RapperRepository rapperRepository) {
        this.rapperRepository = rapperRepository;
    }
    @RequestMapping(value = "rappers")
    public String listRappersInfo(ModelMap model) {
        rappersList = rapperRepository.findAllRappers();
        InstagramAccount rapperWithInstagram = new InstagramAccount();
        rappersWithInstagramsList.clear();
        for(Rapper r : rappersList){
            rapperWithInstagram = rapperRepository.findInstagramById(r.getId());
            if((rapperWithInstagram==null)){
                InstagramAccount newRapperWithInstagram = new InstagramAccount();
                Rapper newRapperWithNoInstagram = new Rapper(r.getId(), r.getName(), r.getDateOfBirth(), null);
                newRapperWithInstagram.setRapper(newRapperWithNoInstagram);
                rappersWithInstagramsList.add(newRapperWithInstagram);
            }
            else{
                rappersWithInstagramsList.add(rapperWithInstagram);
            }

        }
        model.addAttribute("rappers", rappersWithInstagramsList);
        return "rappers";
    }

    @RequestMapping(value="album-list")
    public String showAlbumList(ModelMap model, @RequestParam int id){

        askedRapper = rapperRepository.findById(id).get();
        albumList = rapperRepository.findAlbumsByRapersId(id);
        numberOfAlbums = albumList.size();

        model.addAttribute("albumList", albumList);
        model.addAttribute("numberOfAlbums", numberOfAlbums);
        model.addAttribute("askedRapper", askedRapper);

        return "album-list";
    }

    @InitBinder
    public void initBinderAlbum(WebDataBinder binder) {
        binder.registerCustomEditor(int.class, "numberOfSongs", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.trim().isEmpty()) {
                    setValue(0);
                } else {
                    setValue(Integer.parseInt(text));
                }
            }
        });
    }
    @RequestMapping(value="add-album", method = RequestMethod.GET)
    public String showNewAlbumView(ModelMap model, @RequestParam int id){
        askedRapper = rapperRepository.findById(id).get();
        model.addAttribute("askedRapper", askedRapper);
        Album newAlbum = new Album();
        model.put("album",newAlbum);
        return "add-album";
    }

    @PostMapping("/add-album")
    public String addNewAlbumForRapper(@ModelAttribute("album") Album newAlbum, Model model, BindingResult result, @RequestParam int id) {

        if (result.hasErrors()) {
            return "add-album";
        }
        newAlbum.setRapper(askedRapper);
        model.addAttribute("askedRapper", askedRapper);

        albumRepository.save(newAlbum);
        return "redirect:album-list?id=" + id;
    }
    @RequestMapping(value="delete-album")
    public String deleteAlbum(@RequestParam int albumId, @RequestParam int rapperId){
        albumRepository.deleteById(albumId);
        return "redirect:album-list?id=" + rapperId;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(int.class, "followers", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.trim().isEmpty()) {
                    setValue(0);
                } else {
                    setValue(Integer.parseInt(text));
                }
            }
        });
    }

    @RequestMapping(value="add-rapper", method = RequestMethod.GET)
    public String showNewRapperView(ModelMap model){
        InstagramAccount newIg = new InstagramAccount();
        model.put("ig_account",newIg);
        return "add-rapper";
    }

    @PostMapping("/add-rapper")
    public String addNewInstagramAccountWithRapper(@ModelAttribute("ig_account") InstagramAccount newIgAccount, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "add-rapper";
        }
        if(newIgAccount.getName()==""){
            rapperRepository.save(newIgAccount.getRapper());
        }
        else{
            rapperRepository.save(newIgAccount.getRapper());
            instagramAccountRepository.save(newIgAccount);
        }
        return "redirect:rappers";
    }
    @RequestMapping(value="delete-rapper")
    public String deleteRapper(@RequestParam int id){
        instagramAccountRepository.deleteByRapperId(id);
        rapperRepository.deleteById(id);
        return "redirect:rappers";
    }

    @RequestMapping(value="edit-rapper", method = RequestMethod.GET)
    public String showEditRapperPanel(@RequestParam int id,ModelMap model){
        InstagramAccount igAccountToEdit = rapperRepository.findInstagramById(id);
        if(igAccountToEdit==null){
            igAccountToEdit = new InstagramAccount();
            igAccountToEdit.setRapper(rapperRepository.findById(id).get());
        }
        model.put("ig_account",igAccountToEdit);
        return "add-rapper";
    }

    @RequestMapping(value = "edit-rapper", method = RequestMethod.POST)
    public String addEditedRapper(@ModelAttribute("ig_account") InstagramAccount newIgAccount, Model model, BindingResult result) {
        Rapper rapperToSave = rapperRepository.findById(newIgAccount.getId()).get();

        if (result.hasErrors()) {
            return "redirect:edit-rapper";
        }
        rapperToSave.setName(newIgAccount.getRapper().getName());
        rapperToSave.setDateOfBirth(newIgAccount.getRapper().getDateOfBirth());
        rapperRepository.save(rapperToSave);


        InstagramAccount readenIgAccount = rapperRepository.findInstagramById(newIgAccount.getId());
        if(readenIgAccount==null)
        {
            InstagramAccount igAccountToSave = new InstagramAccount();
            igAccountToSave.setName(newIgAccount.getName());
            igAccountToSave.setFollowers(newIgAccount.getFollowers());
            igAccountToSave.setRapper(rapperToSave);
            instagramAccountRepository.save(igAccountToSave);
        }
        else{
            readenIgAccount.setName(newIgAccount.getName());
            readenIgAccount.setFollowers(newIgAccount.getFollowers());
            readenIgAccount.setRapper(rapperToSave);
            instagramAccountRepository.save(readenIgAccount);
        }

        return "redirect:rappers";
    }


}
