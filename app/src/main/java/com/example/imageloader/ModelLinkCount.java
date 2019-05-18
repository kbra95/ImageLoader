package com.example.imageloader;

public class ModelLinkCount {
        private long id;
        private String URL;
        private int timesSeen;

        public ModelLinkCount(String URL, int timesSeen) {
            setURL(URL);
            setTimesSeen(timesSeen);
        }
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public int getTimesSeen() {
            return timesSeen;
        }

        public void setTimesSeen(int timesSeen) {
            this.timesSeen = timesSeen;
        }

        public void incrementTimesSeen() {
            this.timesSeen += 1;
        }
}
