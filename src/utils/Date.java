package utils;

public class Date {
    private int YY, MM, DD, hh, mm, ss;
    private int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] leapDays = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public Date() {
        this.YY = 2222;
        this.MM = 1;
        this.DD = 1;
        this.hh = 0;
        this.mm = 0;
        this.ss = 0;
    }

    public Date(int YY, int MM, int DD, int hh, int mm, int ss) {
        this.YY = YY;
        this.MM = MM;
        this.DD = DD;
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
    }

    private boolean isLeap() {
        if (this.YY % 4 == 0 && this.YY % 100 != 0)
            return true;
        if (this.YY % 400 == 0)
            return true;
        return false;
    }

    public void update() {
        this.ss++;
        this.check();
    }

    public void check() {
        this.mm += this.ss / 60;
        this.ss %= 60;
        this.hh += this.mm / 60;
        this.mm %= 60;
        this.DD += this.hh / 24;
        this.hh %= 24;
        if (!this.isLeap() && this.DD > this.days[this.MM]) {
            this.DD -= this.days[this.MM];
            this.MM++;
        }
        if (this.isLeap() && this.DD > this.leapDays[this.MM]) {
            this.DD -= this.leapDays[this.MM];
            this.MM++;
        }
        if (this.MM > 12) {
            this.MM = 1;
            this.YY++;
        }
    }

    @Override
    public String toString() { return this.YY + "/" + this.MM + "/" + this.DD + " " + this.hh + ":" + this.mm + ":" + this.ss; }
}
