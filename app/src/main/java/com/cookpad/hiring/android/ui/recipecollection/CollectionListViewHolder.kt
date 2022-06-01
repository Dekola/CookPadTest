package com.cookpad.hiring.android.ui.recipecollection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookpad.hiring.android.R
import com.cookpad.hiring.android.data.entities.Collection
import com.cookpad.hiring.android.databinding.CollectionListItemBinding

class CollectionListViewHolder(private val binding: CollectionListItemBinding, private val favoriteSelectionListener: (Collection) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Collection) {
        Glide.with(binding.root.context)
            .load(item.previewImageUrls.firstOrNull())
            .centerCrop()
            .into(binding.CollectionImageView)

        binding.collectionNameTextView.text = item.title
        binding.descriptionTextView.text = item.description
        binding.RecipeCountTextView.text = binding.root.context.getString(
            R.string.collection_recipe_count,
            item.recipeCount.toString()
        )
        setFavoriteImageResource(item.isFavorite)
        binding.favouriteImageView.setOnClickListener {
            favoriteSelectionListener.invoke(item)
            item.isFavorite = !item.isFavorite
            setFavoriteImageResource(item.isFavorite)
        }
    }

    private fun setFavoriteImageResource(isFavorite:Boolean) {
        val favoriteImage = if (isFavorite) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
        binding.favouriteImageView.setImageResource(favoriteImage)
    }

    companion object {
        fun create(parent: ViewGroup, favoriteSelectionListener: (Collection) -> Unit): CollectionListViewHolder {
            val restaurantListItemBinding =
                CollectionListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return CollectionListViewHolder(restaurantListItemBinding, favoriteSelectionListener)
        }
    }
}