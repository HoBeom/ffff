from os import path
from glob import glob
from tqdm import tqdm
import torch

model = torch.hub.load('harritaylor/torchvggish', 'vggish')
model.eval()
root = './total'
filenames = glob(path.join(root,'*.wav'))
result = torch.zeros((2871,3,128))
for idx, data in enumerate(tqdm(filenames)):
    result[idx] = model.forward(data)
torch.save(result,'waveoutvggish.pt')