package hold;

import com.essaid.views.View;
import java.util.Map;

public interface MapTransformer {

  Map<String, Object> toMap(Object object);

  View fromMap(Map<String, Object> map);

}
