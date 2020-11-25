import torch
import torchaudio
import matplotlib.pyplot as plt
from tqdm import tqdm
from glob import glob
filenames = glob('./total/*')
for idx, filename in tqdm(enumerate(filenames)):
    waveform, sample_rate = torchaudio.load(filename)
    plt.figure()
    plt.plot(waveform.t().numpy())
    plt.savefig('./plotwave/{}.png'.format(idx))
    plt.close()
