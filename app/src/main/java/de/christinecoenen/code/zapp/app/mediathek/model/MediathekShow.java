package de.christinecoenen.code.zapp.app.mediathek.model;


import android.content.Intent;
import android.text.TextUtils;
import android.text.format.DateUtils;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.io.Serializable;


@SuppressWarnings("unused")
public class MediathekShow implements Serializable {

	private static final PeriodFormatter hourPeriodFormatter = new PeriodFormatterBuilder()
		.appendHours()
		.appendSeparatorIfFieldsBefore("h ")
		.appendMinutes()
		.appendSeparatorIfFieldsBefore("m")
		.toFormatter();

	private static final PeriodFormatter secondsPeriodFormatter = new PeriodFormatterBuilder()
		.appendMinutes()
		.appendSeparatorIfFieldsBefore("m ")
		.appendSeconds()
		.appendSeparatorIfFieldsBefore("s")
		.toFormatter();

	private String id;
	private String topic;
	private String title;
	private String description;
	private String channel;
	private int timestamp;
	private long size;
	private String duration;
	private int filmlisteTimestamp;

	@SerializedName("url_website")
	private String websiteUrl;

	@SerializedName("url_subtitle")
	private String subtitleUrl;

	@SerializedName("url_video")
	private String videoUrl;

	@SerializedName("url_video_low")
	private String videoUrlLow;

	@SerializedName("url_video_hd")
	private String videoUrlHd;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public CharSequence getFormattedTimestamp() {
		if (timestamp == 0) {
			return "?";
		}

		long time = DateTimeZone
			.forID("Europe/Berlin")
			.convertLocalToUTC(timestamp * DateUtils.SECOND_IN_MILLIS, false);
		return DateUtils.getRelativeTimeSpanString(time);
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFormattedDuration() {
		int duration;
		try {
			duration = Integer.parseInt(this.duration);
		} catch (NumberFormatException e) {
			return "?";
		}

		Period period = Duration.standardSeconds(duration).toPeriod();
		PeriodFormatter formatter = (period.getHours() > 0) ? hourPeriodFormatter : secondsPeriodFormatter;
		return period.toString(formatter);
	}

	public int getFilmlisteTimestamp() {
		return filmlisteTimestamp;
	}

	public void setFilmlisteTimestamp(int filmlisteTimestamp) {
		this.filmlisteTimestamp = filmlisteTimestamp;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getSubtitleUrl() {
		return subtitleUrl;
	}

	public void setSubtitleUrl(String subtitleUrl) {
		this.subtitleUrl = subtitleUrl;
	}

	public boolean hasSubtitle() {
		return !TextUtils.isEmpty(subtitleUrl);
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public void setVideoUrlLow(String videoUrlLow) {
		this.videoUrlLow = videoUrlLow;
	}

	public void setVideoUrlHd(String videoUrlHd) {
		this.videoUrlHd = videoUrlHd;
	}

	public String getVideoUrl(Quality quality) {
		switch (quality) {
			case Low:
				return videoUrlLow;
			case Medium:
				return videoUrl;
			case High:
				return videoUrlHd;
			default:
				throw new IllegalArgumentException();
		}
	}

	public boolean hasStreamingQuality(Quality quality) {
		String videoUrl = getVideoUrl(quality);
		return isValidStreamingUrl(videoUrl);
	}

	public boolean hasDownloadQuality(Quality quality) {
		String videoUrl = getVideoUrl(quality);
		return isValidDownloadUrl(videoUrl);
	}

	public String getDownloadFileName(Quality quality) {
		String videoUrl = getVideoUrl(quality);
		return getDownloadFileName(videoUrl);
	}

	public String getDownloadFileNameSubtitle() {
		return getDownloadFileName(subtitleUrl);
	}

	public Intent getShareIntentPlain() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, topic + " - " + title);
		intent.putExtra(Intent.EXTRA_TEXT, videoUrl);
		return intent;
	}

	private String getDownloadFileName(String videoUrl) {
		String extension = FilenameUtils.getExtension(videoUrl);

		// needed for samsung devices
		int maxFileNameLength = 120;
		String fileName = title.length() <= maxFileNameLength ?
			title : title.substring(0, maxFileNameLength);

		// replace characters that may crash download manager
		fileName = fileName.replaceAll("[\\\\/:*?\"<>|%]", "-");
		fileName = fileName.replaceAll("\\.\\.\\.", "…");

		return fileName + "." + extension;
	}

	private boolean isValidStreamingUrl(String url) {
		return !TextUtils.isEmpty(url);
	}

	private boolean isValidDownloadUrl(String url) {
		return !TextUtils.isEmpty(url) && !url.endsWith("m3u8") && !url.endsWith("csmil");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MediathekShow that = (MediathekShow) o;

		return id.equals(that.id);

	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@NonNull
	@Override
	public String toString() {
		return "MediathekShow{" +
			"id='" + id + '\'' +
			", topic='" + topic + '\'' +
			", title='" + title + '\'' +
			", description='" + description + '\'' +
			", channel='" + channel + '\'' +
			", timestamp=" + timestamp +
			", size=" + size +
			", duration=" + duration +
			", filmlisteTimestamp=" + filmlisteTimestamp +
			", websiteUrl='" + websiteUrl + '\'' +
			", subtitleUrl='" + subtitleUrl + '\'' +
			", videoUrl='" + videoUrl + '\'' +
			", videoUrlLow='" + videoUrlLow + '\'' +
			", videoUrlHd='" + videoUrlHd + '\'' +
			'}';
	}
}
