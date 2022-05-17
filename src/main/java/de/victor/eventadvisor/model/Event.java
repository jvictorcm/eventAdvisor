package de.victor.eventadvisor.model;

import de.victor.eventadvisor.service.publicApiResponse.PropertiesData;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Event {
    @Id
    private Long id;
    private String bezirk;
    private String bezeichnung;
    private String strasse;
    private Long plz;
    private LocalDate von;
    private LocalDate bis;
    private String zeit;
    private String veranstalter;
    private String mail;
    private String www;
    private String bemerkungen;
    private String _wgs84_lat;
    private String _wgs84_lon;
    private int eventScore = 0;


    public Event(Long id, String bezirk, String bezeichnung, String strasse, Long plz, LocalDate von, LocalDate bis, String zeit, String veranstalter, String mail, String www, String bemerkungen, String _wgs84_lat, String _wgs84_lon) {
        this.id = id;
        this.bezirk = bezirk;
        this.bezeichnung = bezeichnung;
        this.strasse = strasse;
        this.plz = plz;
        this.von = von;
        this.bis = bis;
        this.zeit = zeit;
        this.veranstalter = veranstalter;
        this.mail = mail;
        this.www = www;
        this.bemerkungen = bemerkungen;
        this._wgs84_lat = _wgs84_lat;
        this._wgs84_lon = _wgs84_lon;
    }

    public Event(PropertiesData eventPropertyData) {
        this.id = eventPropertyData.getId();
        this.bezirk = eventPropertyData.getBezirk();
        this.bezeichnung = eventPropertyData.getBezeichnung();
        this.strasse = eventPropertyData.getStrasse();
        this.plz = eventPropertyData.getPlz();
        this.von = eventPropertyData.getVon();
        this.bis = eventPropertyData.getBis();
        this.zeit = eventPropertyData.getZeit();
        this.veranstalter = eventPropertyData.getVeranstalter();
        this.mail = eventPropertyData.getMail();
        this.www = eventPropertyData.getWww();
        this.bemerkungen = eventPropertyData.getBemerkungen();
        this._wgs84_lat = eventPropertyData.get_wgs84_lat();
        this._wgs84_lon = eventPropertyData.get_wgs84_lon();
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezirk() {
        return bezirk;
    }

    public void setBezirk(String bezirk) {
        this.bezirk = bezirk;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public Long getPlz() {
        return plz;
    }

    public void setPlz(Long plz) {
        this.plz = plz;
    }

    public LocalDate getVon() {
        return von;
    }

    public void setVon(LocalDate von) {
        this.von = von;
    }

    public LocalDate getBis() {
        return bis;
    }

    public void setBis(LocalDate bis) {
        this.bis = bis;
    }

    public String getZeit() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }

    public String getVeranstalter() {
        return veranstalter;
    }

    public void setVeranstalter(String veranstalter) {
        this.veranstalter = veranstalter;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getBemerkungen() {
        return bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String get_wgs84_lat() {
        return _wgs84_lat;
    }

    public void set_wgs84_lat(String _wgs84_lat) {
        this._wgs84_lat = _wgs84_lat;
    }

    public String get_wgs84_lon() {
        return _wgs84_lon;
    }

    public void set_wgs84_lon(String _wgs84_lon) {
        this._wgs84_lon = _wgs84_lon;
    }

    public int getEventScore() {
        return eventScore;
    }

    public void setEventScore(int eventScore) {
        this.eventScore = eventScore;
    }
}
