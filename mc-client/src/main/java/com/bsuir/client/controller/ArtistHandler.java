package com.bsuir.client.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bsuir.center.domain.Artist;
import com.bsuir.center.domain.Country;
import com.bsuir.center.domain.Genre;
import com.bsuir.center.domain.Group;
import com.bsuir.center.domain.Person;
import com.bsuir.center.domain.Sex;
import com.bsuir.client.exception.ServiceException;
import com.bsuir.client.service.IGroupService;
import com.bsuir.client.service.IPersonService;

@Controller
@RequestMapping("/artist")
public class ArtistHandler {

	private final String DELETE_PERSON_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/person/{id}";
	private final String PERSON_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/person";
	private final String GROUP_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/group";
	private final String DELETE_GROUP_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/group/{id}";
	private final String UPDATE_PERSON_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/person/{id}";
	private final String UPDATE_GROUP_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/group/{id}";
	private final String GENRE_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/genre";
	private final String COUNTRY_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/country";
	private final String SEX_REST_SERVICE_URI = "http://localhost:8082/mc-server/v1/music/sex";
	private final String DELETE_SUCCESS_MESSAGE = "Артист был успешно удален";
	private final String SAVE_SUCCESS_MESSAGE = "Артист был успешно сохранен. ";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IPersonService personService;

	@Autowired
	private IGroupService groupService;

	@RequestMapping("/show-all")
	public String showAllAtrists(@RequestParam(required = false) Boolean isDeleted, @RequestParam(value = "savedArtist", required = false) String savedArtistName,
			@RequestParam(value = "savedGroup", required = false) String savedGroupName, @RequestParam(value="updatedArtist", required = false) String updatedArtistName,
			@RequestParam(value="updatedGroup", required = false) String updatedGroupName, Model model) {

		List<Person> persons = null;
		List<Group> groups = null;

		if (isDeleted != null) {
			model.addAttribute("deletedMsg", DELETE_SUCCESS_MESSAGE);
		}
		if (savedArtistName != null) {
			model.addAttribute("savedMsg", "Артист '" + savedArtistName + "' был успешно сохранен.");
		} else if (savedGroupName != null) {
			model.addAttribute("savedMsg", "Группа '" + savedGroupName + "' была успешно сохранена.");
		} else if (updatedArtistName != null) {
			model.addAttribute("updatedMsg", "Артист '" + updatedArtistName + "' был успешно обновлен.");
		} else if (updatedGroupName != null) {
			model.addAttribute("updatedMsg", "Группа '" + updatedGroupName + "' была успешно обновлена.");
		}

		try {
			persons = personService.readAll();
			groups = groupService.readAll();
		} catch (ServiceException e) {
			return "redirect:/error";
		}

		model.addAttribute("persons", persons);
		model.addAttribute("groups", groups);

		return "all_artists";
	}

	@RequestMapping("/person/delete/{artistId}")
	public String deletePerson(@PathVariable Integer artistId, RedirectAttributes redirectAttributes) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", artistId);

		restTemplate.delete(DELETE_PERSON_REST_SERVICE_URI, params);
		redirectAttributes.addAttribute("isDeleted", true);
		return "redirect:/artist/show-all";
	}

	@RequestMapping("/group/delete/{artistId}")
	public String deleteGroup(@PathVariable Integer artistId, RedirectAttributes redirectAttributes) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", artistId);

		restTemplate.delete(DELETE_GROUP_REST_SERVICE_URI, params);
		redirectAttributes.addAttribute("isDeleted", true);
		return "redirect:/artist/show-all";
	}

	@RequestMapping(value = "/person/show_save_tab", method = RequestMethod.GET)
	public String showPersonSaveTab(Model model) {
		List<Genre> genres = Arrays.asList(restTemplate.getForEntity(GENRE_REST_SERVICE_URI, Genre[].class).getBody());
		List<Country> countries = Arrays.asList(restTemplate.getForEntity(COUNTRY_REST_SERVICE_URI, Country[].class).getBody());
		List<Sex> sexs = Arrays.asList(restTemplate.getForEntity(SEX_REST_SERVICE_URI, Sex[].class).getBody());

		model.addAttribute("genres", genres);
		model.addAttribute("countries", countries);
		model.addAttribute("sexs", sexs);
		return "save_artist";
	}

	@RequestMapping(value = "/group/show_save_tab", method = RequestMethod.GET)
	public String showGroupSaveTab(Model model) {
		List<Genre> genres = Arrays.asList(restTemplate.getForEntity(GENRE_REST_SERVICE_URI, Genre[].class).getBody());
		List<Country> countries = Arrays.asList(restTemplate.getForEntity(COUNTRY_REST_SERVICE_URI, Country[].class).getBody());

		model.addAttribute("genres", genres);
		model.addAttribute("countries", countries);
		return "save_group";
	}

	@RequestMapping(value = "/person/save", method = RequestMethod.POST)
	public String savePerson(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthday, @RequestParam String artistSiteUrl,
			@RequestParam(name = "genre") Integer genreId, @RequestParam(name = "sex") Integer sexId, @RequestParam(name = "country") Integer countryId, RedirectAttributes redirectAttributes)
					throws ParseException {

		Sex sex = new Sex();
		sex.setSexId(sexId);
		Genre genre = new Genre();
		genre.setGenreId(genreId);

		Country country = new Country();
		country.setCountryId(countryId);

		Artist artist = new Artist();
		artist.setGenre(genre);
		artist.setCountry(country);
		artist.setArtistSiteUrl(artistSiteUrl);

		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setArtist(artist);
		person.setBirthday(new SimpleDateFormat("yyyy-mm-dd").parse(birthday));
		person.setSex(sex);

		restTemplate.postForObject(PERSON_REST_SERVICE_URI, person, Integer.class);
		redirectAttributes.addAttribute("savedArtist", firstName);
		return "redirect:/artist/show-all";
	}

	@RequestMapping(value = "/group/save", method = RequestMethod.POST)
	public String saveGroup(@RequestParam String groupName, @RequestParam String artistSiteUrl, @RequestParam(name = "genre") Integer genreId, @RequestParam(name = "country") Integer countryId,
			RedirectAttributes redirectAttributes) {

		Genre genre = new Genre();
		genre.setGenreId(genreId);

		Country country = new Country();
		country.setCountryId(countryId);

		Artist artist = new Artist();
		artist.setGenre(genre);
		artist.setCountry(country);
		artist.setArtistSiteUrl(artistSiteUrl);

		Group group = new Group();
		group.setGroupName(groupName);
		group.setArtist(artist);

		restTemplate.postForObject(GROUP_REST_SERVICE_URI, group, Integer.class);
		redirectAttributes.addAttribute("savedGroup", groupName);
		return "redirect:/artist/show-all";
	}

	@RequestMapping(value = "/person/show_edit_tab/{artistId}", method = RequestMethod.GET)
	public String showArtistEditTab(@PathVariable Integer artistId, Model model) {
		Person person = restTemplate.getForObject(PERSON_REST_SERVICE_URI + "/" + artistId, Person.class);
		List<Genre> genres = Arrays.asList(restTemplate.getForEntity(GENRE_REST_SERVICE_URI, Genre[].class).getBody());
		List<Country> countries = Arrays.asList(restTemplate.getForEntity(COUNTRY_REST_SERVICE_URI, Country[].class).getBody());
		List<Sex> sexs = Arrays.asList(restTemplate.getForEntity(SEX_REST_SERVICE_URI, Sex[].class).getBody());

		model.addAttribute("genres", genres);
		model.addAttribute("countries", countries);
		model.addAttribute("sexs", sexs);
		model.addAttribute("person", person);
		return "save_artist";
	}

	@RequestMapping(value = "/group/show_edit_tab/{groupId}", method = RequestMethod.GET)
	public String showGrouptEditTab(@PathVariable Integer groupId, Model model) {
		Group group = restTemplate.getForObject(GROUP_REST_SERVICE_URI + "/" + groupId, Group.class);
		List<Genre> genres = Arrays.asList(restTemplate.getForEntity(GENRE_REST_SERVICE_URI, Genre[].class).getBody());
		List<Country> countries = Arrays.asList(restTemplate.getForEntity(COUNTRY_REST_SERVICE_URI, Country[].class).getBody());

		model.addAttribute("genres", genres);
		model.addAttribute("countries", countries);
		model.addAttribute("group", group);
		return "save_group";
	}

	@RequestMapping(value = "/person/update/{personId}", method = RequestMethod.POST)
	public String updatePerson(@PathVariable Integer personId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String birthday, @RequestParam String artistSiteUrl,
			@RequestParam(name = "genre") Integer genreId, @RequestParam(name = "sex") Integer sexId, @RequestParam(name = "country") Integer countryId, RedirectAttributes redirectAttributes)
					throws ParseException {

		Sex sex = new Sex();
		sex.setSexId(sexId);
		Genre genre = new Genre();
		genre.setGenreId(genreId);

		Country country = new Country();
		country.setCountryId(countryId);

		Artist artist = new Artist();
		artist.setArtistId(personId);
		artist.setGenre(genre);
		artist.setCountry(country);
		artist.setArtistSiteUrl(artistSiteUrl);

		Person person = new Person();
		person.setArtistId(personId);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setArtist(artist);
		person.setBirthday(new SimpleDateFormat("yyyy-mm-dd").parse(birthday));
		person.setSex(sex);

		restTemplate.postForObject(PERSON_REST_SERVICE_URI + "/" + personId, person, Integer.class);
		redirectAttributes.addAttribute("updatedArtist", firstName);
		return "redirect:/artist/show-all";
	}

	@RequestMapping(value = "/group/update/{groupId}", method = RequestMethod.POST)
	public String updateGroup(@PathVariable Integer groupId, @RequestParam String groupName, @RequestParam String artistSiteUrl, @RequestParam(name = "genre") Integer genreId,
			@RequestParam(name = "country") Integer countryId, RedirectAttributes redirectAttributes) {

		Genre genre = new Genre();
		genre.setGenreId(genreId);

		Country country = new Country();
		country.setCountryId(countryId);

		Artist artist = new Artist();
		artist.setArtistId(groupId);
		artist.setGenre(genre);
		artist.setCountry(country);
		artist.setArtistSiteUrl(artistSiteUrl);

		Group group = new Group();
		group.setArtistId(groupId);
		group.setGroupName(groupName);
		group.setArtist(artist);

		restTemplate.postForObject(GROUP_REST_SERVICE_URI + "/" + groupId, group, Integer.class);
		redirectAttributes.addAttribute("updatedGroup", groupName);
		return "redirect:/artist/show-all";
	}
}
