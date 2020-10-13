package ai.lenna.spsiapp.berita;

import java.util.ArrayList;

public class BeritaResponse {
    private int code;
    private boolean success;
    private ArrayList<Berita> berita;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Berita> getBerita() {
        return berita;
    }

    public void setBerita(ArrayList<Berita> berita) {
        this.berita = berita;
    }
}
