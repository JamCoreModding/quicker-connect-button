package {{group}}.{{mod_id}}.fabric;

import {{group}}.{{mod_id}}.fabriclike.{{main_class}}FabricLike;
import net.fabricmc.api.ModInitializer;

public class {{main_class}}Fabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        {{main_class}}FabricLike.init();
    }
}
