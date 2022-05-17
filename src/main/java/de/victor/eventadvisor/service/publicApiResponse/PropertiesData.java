package de.victor.eventadvisor.service.publicApiResponse;

import java.time.LocalDate;

public class PropertiesData {
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
}
