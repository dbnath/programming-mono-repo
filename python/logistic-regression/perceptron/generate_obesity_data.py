import numpy as np
import pandas as pd

# Set random seed for reproducibility
np.random.seed(42)

# Generate synthetic data
num_samples = 1000
heights = np.random.normal(1.7, 0.1, num_samples)  # Heights in meters (mean=1.7m, std=0.1m)
weights = np.random.normal(70, 15, num_samples)   # Weights in kg (mean=70kg, std=15kg)

# Calculate BMI
bmi = weights / (heights ** 2)

# Define obesity threshold (BMI >= 30 is considered obese)
obese = (bmi >= 30).astype(int)

# Create a DataFrame
data = pd.DataFrame({
    'x1': weights,
    'x2': heights,
    'z': obese
})

# Save to CSV
data.to_csv('obesity_data.csv', index=False)

print("Obesity data generated and saved to 'obesity_data.csv'.")