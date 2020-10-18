package ai.lenna.spsiapp.strukturorganisasi;

import java.util.ArrayList;

public class StrukturResponse {
    private int code;
    private boolean success;
    private ArrayList<Struktur> struktur;

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

    public ArrayList<Struktur> getS() {
        return struktur;
    }

    public void setS(ArrayList<Struktur> s) {
        this.struktur = s;
    }
}
