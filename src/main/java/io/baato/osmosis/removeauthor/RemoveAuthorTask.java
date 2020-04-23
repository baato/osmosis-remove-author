package io.baato.osmosis.removeauthor;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.openstreetmap.osmosis.core.container.v0_6.BoundContainer;
import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.container.v0_6.EntityProcessor;
import org.openstreetmap.osmosis.core.container.v0_6.NodeContainer;
import org.openstreetmap.osmosis.core.container.v0_6.RelationContainer;
import org.openstreetmap.osmosis.core.container.v0_6.WayContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.CommonEntityData;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.OsmUser;
import org.openstreetmap.osmosis.core.domain.v0_6.Relation;
import org.openstreetmap.osmosis.core.domain.v0_6.RelationMember;
import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;
import org.openstreetmap.osmosis.core.task.v0_6.SinkSource;

public class RemoveAuthorTask implements SinkSource, EntityProcessor {
	private Sink sink;

	public void initialize(Map<String, Object> metaData) {
//		sink.initialize(metaData);
	}

	public void setSink(Sink sink) {
		this.sink = sink;
	}

	public void process(BoundContainer bound) {
		// TODO Auto-generated method stub
		sink.process(bound);
	}

	public void process(NodeContainer node) {
		// TODO Auto-generated method stub
		double lat = node.getEntity().getLatitude();
		double lon = node.getEntity().getLongitude();

		Collection<Tag> nodeTags = node.getEntity().getTags();

		CommonEntityData nodeCed = new CommonEntityData(node.getEntity().getId(), 1, node.getEntity().getTimestamp(),
				OsmUser.NONE, 1, nodeTags);

		sink.process(new NodeContainer(new Node(nodeCed, lat, lon)));

	}

	public void process(WayContainer way) {
		// TODO Auto-generated method stub
		List<WayNode> wayNodes = way.getEntity().getWayNodes();
		Collection<Tag> wayTags = way.getEntity().getTags();
		CommonEntityData wayCed = new CommonEntityData(way.getEntity().getId(), 1, way.getEntity().getTimestamp(),
				OsmUser.NONE, 1, wayTags);

		sink.process(new WayContainer(new Way(wayCed, wayNodes)));

	}

	public void process(RelationContainer relation) {
		List<RelationMember> relationMembers = relation.getEntity().getMembers();
		Collection<Tag> relationTags = relation.getEntity().getTags();

		CommonEntityData relationCed = new CommonEntityData(relation.getEntity().getId(), 1,
				relation.getEntity().getTimestamp(), OsmUser.NONE, 1, relationTags);

		sink.process(new RelationContainer(new Relation(relationCed, relationMembers)));
	}

	public void process(EntityContainer entityContainer) {
		entityContainer.process(this);
		// TODO Auto-generated method stub
//		sink.process(entityContainer);

	}

	public void close() {
		// TODO Auto-generated method stub
		sink.close();

	}

	public void complete() {
		// TODO Auto-generated method stub
		sink.complete();

	}

}
