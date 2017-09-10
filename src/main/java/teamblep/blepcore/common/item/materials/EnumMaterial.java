package teamblep.blepcore.common.item.materials;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import teamblep.blepcore.common.item.ItemHandler;

import java.awt.*;
import java.util.*;

/**
 * @author Blue
 */
public enum EnumMaterial implements IStringSerializable
{
    // ##### VANILLA #####
    IRON(0, EnumSubstance.IRON, EnumMaterialType.DUST),
    GOLD(1, EnumSubstance.GOLD, EnumMaterialType.DUST),
    STONE(2, EnumSubstance.STONE, EnumMaterialType.DUST),

    // ##### NON-ALLOYED METALS #####
    COPPER(3, EnumSubstance.COPPER, EnumMaterialType.ORE, EnumMaterialType.BLOCK, EnumMaterialType.INGOT, EnumMaterialType.NUGGET, EnumMaterialType.DUST, EnumMaterialType.DIRTY_DUST),
    TIN(4, EnumSubstance.TIN, EnumMaterialType.ORE, EnumMaterialType.BLOCK, EnumMaterialType.INGOT, EnumMaterialType.NUGGET, EnumMaterialType.DUST, EnumMaterialType.DIRTY_DUST),
    LEAD(5, EnumSubstance.LEAD, EnumMaterialType.ORE, EnumMaterialType.BLOCK, EnumMaterialType.INGOT, EnumMaterialType.NUGGET, EnumMaterialType.DUST, EnumMaterialType.DIRTY_DUST),
    SILVER(6, EnumSubstance.SILVER, EnumMaterialType.ORE, EnumMaterialType.BLOCK, EnumMaterialType.INGOT, EnumMaterialType.NUGGET, EnumMaterialType.DUST, EnumMaterialType.DIRTY_DUST);

    public static final EnumMaterial[] MATERIALS;

    static
    {
        MATERIALS = new EnumMaterial[values().length];

        for (EnumMaterial type : values())
        {
            MATERIALS[type.getMeta()] = type;
        }
    }

    private EnumSubstance substance;
    private int meta;
    private Set<EnumMaterialType> types;

    EnumMaterial(int meta, EnumSubstance substance, EnumMaterialType... types)
    {
        this.substance = substance;
        this.meta = meta;
        this.types = new HashSet<>(types.length);

        Collections.addAll(this.types, types);
    }

    @Override
    public String getName()
    {
        return this.substance.getName();
    }

    public String getNameOfType(EnumMaterialType type)
    {
        return this.getName() + "_" + type.getName();
    }

    public int getMeta() {
        return meta;
    }

    public int getMetadata(EnumMaterialType type)
    {
        return meta * 8 + type.getMetadata();
    }

    public Set<EnumMaterialType> getTypes()
    {
        return types;
    }

    public Color getColor()
    {
        return substance.getColor();
    }

    public boolean hasType(EnumMaterialType type) {
        return getTypes().contains(type);
    }

    public ItemStack getStackForType(EnumMaterialType type)
    {
        return getStackForType(type, 1);
    }

    public ItemStack getStackForType(EnumMaterialType type, int amount)
    {
        return new ItemStack(ItemHandler.item_material, amount, getMetadata(type));
    }
}
