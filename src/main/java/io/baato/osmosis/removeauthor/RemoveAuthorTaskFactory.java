package io.baato.osmosis.removeauthor;

import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
import org.openstreetmap.osmosis.core.pipeline.v0_6.SinkSourceManager;

public class RemoveAuthorTaskFactory extends TaskManagerFactory {

	@Override
	protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
		// the provided configuration includes the argument you pass to osmosis,
		// which can be used to config the task to be created
		// e.g. if you started osmosis like this:
		// osmosis --read-pbf latest.osm.pbf --my-plugin key=value
		// you can get fetch the passed argument like this:
		// String value = getStringArgument(taskConfiguration, "key", null);

		RemoveAuthorTask task = new RemoveAuthorTask();
		return new SinkSourceManager(taskConfig.getId(), task, taskConfig.getPipeArgs());

	}

}
