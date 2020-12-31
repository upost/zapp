package de.christinecoenen.code.zapp.models.channels;

import java.util.List;

public interface IChannelList extends Iterable<ChannelModel> {

	ChannelModel get(int index);
	ChannelModel get(String id);
	int size();
	List<ChannelModel> getList();
	int indexOf(String channelId);
}