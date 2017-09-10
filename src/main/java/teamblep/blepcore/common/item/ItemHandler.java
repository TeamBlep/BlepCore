package teamblep.blepcore.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamblep.blepcore.common.BlepCore;
import teamblep.blepcore.common.item.core.ItemBase;
import teamblep.blepcore.common.item.materials.EnumMaterial;
import teamblep.blepcore.common.item.materials.EnumMaterialType;
import teamblep.blepcore.common.item.materials.EnumRegistryType;
import teamblep.blepcore.common.item.materials.ItemMaterial;

/**
 * @author Blue
 */
@Mod.EventBusSubscriber
public class ItemHandler
{
    public static final ItemBase item_material = new ItemMaterial();

    @SubscribeEvent
    public static void registerItemsEvent(Register<Item> event)
    {
        registerItems(event.getRegistry());
    }

    private static void registerItems(IForgeRegistry<Item> registry)
    {
        registry.register(item_material);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModels()
    {
        for (EnumMaterialType materialType : EnumMaterialType.values())
        {
            if (materialType.getRegistryType() == EnumRegistryType.ITEM)
            {
                for (EnumMaterial material : EnumMaterial.MATERIALS)
                {
                    if (material.hasType(materialType))
                    {
                        ItemStack mat = new ItemStack(item_material, 1, material.getMetadata(materialType));
                        BlepCore.proxy.registerModel(mat, item_material.getRegistryName(), "variant=" + material.getNameOfType(materialType));
                    }
                }
            }
        }
    }
}
