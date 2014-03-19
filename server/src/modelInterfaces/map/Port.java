package modelInterfaces.map;

import model.map.LocationImpl;

/**
 * Created by qzcx on 3/18/14.
 */
public interface Port {
    void init(String inputResource,
              LocationImpl location,
              String orientation,
              int ratio,
              VertexLocation validVertex1,
              VertexLocation validVertex2);

    String getInputResource();

    void setInputResource(String inputResource);

    LocationImpl getLocation();

    void setLocation(LocationImpl location);

    String getOrientation();

    void setOrientation(String orientation);

    int getRatio();

    void setRatio(int ratio);

    VertexLocation getValidVertex1();

    void setValidVertex1(VertexLocation validVertex1);

    VertexLocation getValidVertex2();

    void setValidVertex2(VertexLocation validVertex2);
}
