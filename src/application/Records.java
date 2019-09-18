package application;

public class Records {

	String id;
	String name;
	String artist;
	String genre;
	String year;

	public Records() {
	}

	public Records(String id, String name, String artist, String genre, String year) {
		setId(id);
		setName(name);
		setArtist(artist);
		setGenre(genre);
		setYear(year);
	}

	public Records(String row) {
		String[] fiedls = row.split(",\\s*");
		setId(fiedls[0]);
		setName(fiedls[1]);
		setArtist(fiedls[2]);
		setGenre(fiedls[3]);
		setYear(fiedls[4]);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id.matches("[\\d]{4}")) {
			this.id = id;
		} else
			throw new IllegalArgumentException("Error: Record ID must be in the form 1234");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null && !name.isEmpty()) {
			this.name = name;
		} else
			throw new IllegalArgumentException("Error: You must enter a record name.");
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		if (artist != null && !artist.isEmpty()) {
			this.artist = artist;
		} else
			throw new IllegalArgumentException("Error: You must enter an artist name.");
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if (genre != null && !genre.isEmpty() && genre.matches("[a-zA-Z]+")) {
			this.genre = genre;
		} else
			throw new IllegalArgumentException("Error: You must enter a valid genre.");
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		if (!year.isEmpty() && (year.matches("[1][9][5-9][0-9]") || year.matches("[2][0][0-1][0-9]")) && year != null) {
			this.year = year;
		} else
			throw new IllegalArgumentException("Error: You must enter a valid year of release. (1950 - present)");
	}

}
