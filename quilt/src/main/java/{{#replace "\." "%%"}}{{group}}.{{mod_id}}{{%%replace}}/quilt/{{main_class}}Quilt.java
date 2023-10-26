package {{group}}.{{mod_id}}.quilt;

import {{group}}.{{mod_id}}.fabriclike.{{main_class}}FabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class {{main_class}}Quilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        {{ main_class }}FabricLike.init();
    }
}
