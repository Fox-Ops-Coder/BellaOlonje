package com.foxdev.bellaolonje.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foxdev.bellaolonje.R;
import com.foxdev.bellaolonje.databinding.MealItemBinding;
import com.foxdev.bellaolonje.objects.Food;
import com.squareup.picasso.Picasso;

public final class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealViewHolder> {

    private Food[] meals = new Food[0];

    public MealsAdapter() {}

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MealItemBinding mealItemBinding = MealItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MealViewHolder(mealItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Food meal = meals[position];
        holder.Bind(meal);

        Picasso.get()
                .load(meal.strMealThumb)
                .into((ImageView) holder.itemView.findViewById(R.id.mealImage));
    }

    @Override
    public int getItemCount() {
        return meals.length;
    }

    public void SetFood(Food[] foods) {
        notifyItemRangeRemoved(0, this.meals.length);
        meals = foods;
        notifyItemRangeChanged(0, meals.length);
    }

    protected static final class MealViewHolder extends RecyclerView.ViewHolder {
        private final MealItemBinding mealItemBinding;

        public MealViewHolder(@NonNull MealItemBinding binding) {
            super(binding.getRoot());
            mealItemBinding = binding;
        }

        public void Bind(@NonNull Food meal) {
            mealItemBinding.setMeal(meal);
            mealItemBinding.executePendingBindings();
        }
    }
}
