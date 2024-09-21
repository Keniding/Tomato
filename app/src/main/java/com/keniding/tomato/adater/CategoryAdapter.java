package com.keniding.tomato.adater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.keniding.tomato.R;
import com.keniding.tomato.model.Category;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final List<Category> categories;

    public CategoryAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void updateCategories(List<Category> newCategories) {
        int oldSize = this.categories.size();
        this.categories.clear();
        this.categories.addAll(newCategories);

        if (oldSize < newCategories.size()) {
            notifyItemRangeInserted(oldSize, newCategories.size() - oldSize);
        } else if (oldSize > newCategories.size()) {
            notifyItemRangeRemoved(newCategories.size(), oldSize - newCategories.size());
        }
        notifyItemRangeChanged(0, Math.min(oldSize, newCategories.size()));
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView categoryName;
        private final TextView categoryDescription;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryDescription = itemView.findViewById(R.id.categoryDescription);
        }

        void bind(Category category) {
            categoryName.setText(category.getNombre());
            categoryDescription.setText(category.getDescripcion());
        }
    }
}
