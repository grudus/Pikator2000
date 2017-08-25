package com.grudus.olx.browser;

public class OfferDto {
    private final String title;
    private final String place;
    private final String price;

    public OfferDto(String title, String place, String price) {
        this.title = title;
        this.place = place;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferDto offer = (OfferDto) o;

        if (title != null ? !title.equals(offer.title) : offer.title != null) return false;
        if (place != null ? !place.equals(offer.place) : offer.place != null) return false;
        return price != null ? price.equals(offer.price) : offer.price == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("{%s :: %s :: %s}", title, price, place);
    }
}
